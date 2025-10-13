package com.anonymousv18.identity.service.iml;

import com.anonymousv18.identity.entity.InvalidatedTokenEntity;
import com.anonymousv18.identity.entity.UserEntity;
import com.anonymousv18.identity.exception.AppException;
import com.anonymousv18.identity.exception.ErrorCode;
import com.anonymousv18.identity.repository.IInvalidatedTokenRepository;
import com.anonymousv18.identity.repository.IUserRepository;
import com.anonymousv18.identity.service.IAuthService;
import com.anonymousv18.identity.util.JwtUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthService implements IAuthService {

    IUserRepository userRepository;
    IInvalidatedTokenRepository invalidatedTokenRepository;
    JwtUtil jwtUtil;

    @Override
    public String authentication(String username, String password) {
        UserEntity user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(password, user.getPassword()) ? jwtUtil.generateToken(user) : null;
    }

    @Override
    public boolean introspectToken(String token) throws ParseException, JOSEException {
        boolean isTokenValid = true;

        try {
            jwtUtil.verifyToken(token, false);
        } catch (AppException _) {
            isTokenValid = false;
        }

        return isTokenValid;
    }
    
    @Override
    public void logout(String token) throws ParseException, JOSEException {
        try {
            SignedJWT signedJWT = jwtUtil.verifyToken(token, true);

            String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            InvalidatedTokenEntity invalidatedTokenEntity =
                    InvalidatedTokenEntity.builder()
                            .id(jwtId)
                            .expiryTime(expiryTime)
                            .build();
            invalidatedTokenRepository.save(invalidatedTokenEntity);
        } catch (AppException _) {
            log.info("Token invalid !");
        }
    }

    @Override
    public String refreshToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtUtil.verifyToken(token, true);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedTokenEntity invalidatedTokenEntity =
                InvalidatedTokenEntity.builder()
                        .id(jwtId)
                        .expiryTime(expiryTime)
                        .build();
        invalidatedTokenRepository.save(invalidatedTokenEntity);

        String username = signedJWT.getJWTClaimsSet().getSubject();
        UserEntity userEntity = userRepository.findOneByUsername(username);
        if (userEntity == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return jwtUtil.generateToken(userEntity);
    }
}
