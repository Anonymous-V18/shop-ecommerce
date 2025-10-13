package com.anonymousv18.profile.controller;

import com.anonymousv18.profile.dto.request.UserProfileCreationRequest;
import com.anonymousv18.profile.dto.response.UserProfileResponse;
import com.anonymousv18.profile.service.IUserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/internal")
public class InternalUserProfileController {

    IUserProfileService userProfileService;

    @PostMapping("/user/{userId}/profile/insert")
    public UserProfileResponse createUserProfile(
            @PathVariable(name = "userId") String userId,
            @RequestBody UserProfileCreationRequest request) {
        return userProfileService.createUserProfile(userId, request);
    }

}
