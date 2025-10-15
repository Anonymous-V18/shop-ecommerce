package com.anonymousv18.identity.controller;

import com.anonymousv18.identity.dto.request.IntrospectRequest;
import com.anonymousv18.identity.dto.request.LoginRequest;
import com.anonymousv18.identity.dto.request.LogoutRequest;
import com.anonymousv18.identity.dto.request.RefreshRequest;
import com.anonymousv18.identity.dto.response.ApiResponse;
import com.anonymousv18.identity.dto.response.IntrospectResponse;
import com.anonymousv18.identity.dto.response.LoginResponse;
import com.anonymousv18.identity.dto.response.RefreshResponse;
import com.anonymousv18.identity.service.IAuthService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthController {

    IAuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest request) {
        String token = authService.authentication(request.getUsername(), request.getPassword());
        LoginResponse response = new LoginResponse(token);
        return ApiResponse.<LoginResponse>builder().result(response).build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        String token = request.getToken();
        authService.logout(token);
        return ApiResponse.<String>builder().message("Logout successfully !").build();
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshResponse> refreshToken(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        String token = request.getToken();
        String newToken = authService.refreshToken(token);
        RefreshResponse response = new RefreshResponse(newToken);
        return ApiResponse.<RefreshResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authentication(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        boolean isValid = authService.introspectToken(request.getToken());
        IntrospectResponse response = new IntrospectResponse(isValid);
        return ApiResponse.<IntrospectResponse>builder().result(response).build();
    }

}
