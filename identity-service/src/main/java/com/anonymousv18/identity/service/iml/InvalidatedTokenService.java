package com.anonymousv18.identity.service.iml;

import com.anonymousv18.identity.entity.InvalidatedTokenEntity;
import com.anonymousv18.identity.repository.IInvalidatedTokenRepository;
import com.anonymousv18.identity.service.IInvalidatedTokenService;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvalidatedTokenService implements IInvalidatedTokenService {

    IInvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    public void insert(SignedJWT signedJWT) throws ParseException {
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedTokenEntity invalidatedTokenEntity =
                InvalidatedTokenEntity.builder()
                        .id(jwtId)
                        .expiryTime(expiryTime)
                        .build();

        invalidatedTokenRepository.save(invalidatedTokenEntity);
    }
}
