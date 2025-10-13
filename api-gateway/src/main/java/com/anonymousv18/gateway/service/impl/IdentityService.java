package com.anonymousv18.gateway.service.impl;

import com.anonymousv18.gateway.dto.request.IntrospectRequest;
import com.anonymousv18.gateway.dto.response.ApiResponse;
import com.anonymousv18.gateway.dto.response.IntrospectResponse;
import com.anonymousv18.gateway.repository.IIdentityClient;
import com.anonymousv18.gateway.service.IIdentityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService implements IIdentityService {

    IIdentityClient identityClient;

    @Override
    public Mono<ApiResponse<IntrospectResponse>> introspect(String token) {
        return identityClient.introspect(IntrospectRequest.builder().token(token).build());
    }

}
