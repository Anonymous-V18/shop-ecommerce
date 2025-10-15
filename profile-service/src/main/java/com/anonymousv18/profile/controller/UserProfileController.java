package com.anonymousv18.profile.controller;

import com.anonymousv18.profile.dto.request.UserProfileRequest;
import com.anonymousv18.profile.dto.response.ApiResponse;
import com.anonymousv18.profile.dto.response.UserProfileResponse;
import com.anonymousv18.profile.service.IUserProfileService;
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
public class UserProfileController {

    IUserProfileService userProfileService;

    @PutMapping("/profiles/update/{id}")
    public ApiResponse<UserProfileResponse> update(@PathVariable("id") String id,
                                                   @RequestBody UserProfileRequest userProfileRequest) {
        UserProfileResponse response = userProfileService.update(id, userProfileRequest);
        return ApiResponse.<UserProfileResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping("/profiles/get")
    public ApiResponse<UserProfileResponse> findById(@RequestParam("id") String id) {
        UserProfileResponse response = userProfileService.findById(id);
        return ApiResponse.<UserProfileResponse>builder()
                .result(response)
                .build();
    }

    @GetMapping("/profiles/get-all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<List<UserProfileResponse>> findAll() {
        List<UserProfileResponse> response = userProfileService.getAll();
        return ApiResponse.<List<UserProfileResponse>>builder()
                .result(response)
                .build();
    }

}
