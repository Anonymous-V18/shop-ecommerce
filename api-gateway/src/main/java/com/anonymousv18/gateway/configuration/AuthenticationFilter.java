package com.anonymousv18.gateway.configuration;

import com.anonymousv18.gateway.dto.response.ApiResponse;
import com.anonymousv18.gateway.service.IIdentityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {

    IIdentityService identityService;
    ObjectMapper objectMapper;

    @NonFinal
    final String[] PUBLIC_ENDPOINTS = {
            "/identity/auth/.*", "/identity/users/register",
            "/identity/roles/get-all", "/identity/permissions/get-all",
            "/address/wards/get-all", "/address/provinces/get-all", "/address/districts/get-all"
    };

    @Value("${app.api-prefix}")
    @NonFinal
    private String apiPrefix;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (isPublicEndPoints(exchange.getRequest()))
            return chain.filter(exchange);

        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeader)) {
            return this.unauthenticated(exchange.getResponse());
        }

        String token = authHeader.getFirst().replace("Bearer", "");

        return identityService.introspect(token).flatMap(
                introspectResponseApiResponse -> {
                    if (introspectResponseApiResponse.getResult().isAuthenticated())
                        return chain.filter(exchange);
                    else
                        return this.unauthenticated(exchange.getResponse());
                }
        ).onErrorResume(throwable -> unauthenticated(exchange.getResponse()));

    }

    @Override
    public int getOrder() {
        return -1;
    }

    private Mono<Void> unauthenticated(ServerHttpResponse response) {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(401)
                .message("Unauthenticated !")
                .build();

        String body;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }

    private boolean isPublicEndPoints(ServerHttpRequest request) {
        return Arrays.stream(PUBLIC_ENDPOINTS)
                .anyMatch(endpoint ->
                        request.getURI().getPath().matches(apiPrefix + endpoint)
                );
    }
}
