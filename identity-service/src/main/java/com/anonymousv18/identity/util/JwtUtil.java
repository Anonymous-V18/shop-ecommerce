package com.anonymousv18.identity.util;

import com.anonymousv18.identity.entity.UserEntity;
import com.anonymousv18.identity.exception.AppException;
import com.anonymousv18.identity.exception.ErrorCode;
import com.anonymousv18.identity.repository.IInvalidatedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.SECRET_KEY}")
    private String secretKey;

    @Value("${jwt.VALID_DURATION}")
    private long validDuration;

    @Value("${jwt.REFRESHABLE_DURATION}")
    private long refreshableDuration;

    @Autowired
    private IInvalidatedTokenRepository invalidatedTokenRepository;

    public String generateToken(UserEntity userEntity) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userEntity.getUsername())
                .issuer("anonymous_v18.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(validDuration, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(userEntity))
                .build();

        Payload payload = jwtClaimsSet.toPayload();

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildScope(UserEntity userEntity) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(userEntity.getRoles())) {
            userEntity.getRoles().forEach(role ->
                    {
                        stringJoiner.add("ROLE_" + role.getCode());
                        if (!CollectionUtils.isEmpty(role.getPermissions())) {
                            role.getPermissions().forEach(permissionEntity -> stringJoiner.add(permissionEntity.getName()));
                        }
                    }
            );
        }
        return stringJoiner.toString();
    }

    public SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new MACVerifier(secretKey.getBytes());
        boolean isTokenValid = signedJWT.verify(verifier);

        Date expirationTime = isRefresh
                ? new Date(
                signedJWT.getJWTClaimsSet().getIssueTime().toInstant()
                        .plus(refreshableDuration, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean isTokenExpired = expirationTime.before(new Date());

        if (!(isTokenValid && !isTokenExpired)) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        boolean isTokenExistedInBlackList = invalidatedTokenRepository.existsById(jwtId);
        if (isTokenExistedInBlackList) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }


}
