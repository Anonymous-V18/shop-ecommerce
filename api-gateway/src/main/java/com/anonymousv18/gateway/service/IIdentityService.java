package com.anonymousv18.gateway.service;

import com.anonymousv18.gateway.dto.response.ApiResponse;
import com.anonymousv18.gateway.dto.response.IntrospectResponse;
import reactor.core.publisher.Mono;

public interface IIdentityService {

    Mono<ApiResponse<IntrospectResponse>> introspect(String token);

}
