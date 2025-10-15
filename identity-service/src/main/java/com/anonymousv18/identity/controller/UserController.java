package com.anonymousv18.identity.controller;

import com.anonymousv18.identity.dto.UserDTO;
import com.anonymousv18.identity.dto.request.SignupRequest;
import com.anonymousv18.identity.dto.response.ApiResponse;
import com.anonymousv18.identity.service.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {

    IUserService userService;

    @PostMapping("/register")
    public ApiResponse<UserDTO> createAccountUser(@Valid @RequestBody SignupRequest signupRequest) {
        UserDTO userDTO = userService.createUser(signupRequest);
        return ApiResponse.<UserDTO>builder().result(userDTO).build();
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<UserDTO>> showAllUsers() {
        return ApiResponse.<List<UserDTO>>builder()
                .result(userService.showAllUsers())
                .build();
    }

}
