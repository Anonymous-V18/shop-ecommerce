package com.anonymousv18.identity.service;

import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface IInvalidatedTokenService {

    void insert(SignedJWT signedJWT) throws ParseException;
}
