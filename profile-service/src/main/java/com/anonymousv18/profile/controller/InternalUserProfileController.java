package com.anonymousv18.profile.controller;

import com.anonymousv18.profile.dto.request.UserProfileRequest;
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

    @PostMapping("/users/{userId}/profiles/insert")
    public UserProfileResponse insert(
            @PathVariable(name = "userId") String userId,
            @RequestBody UserProfileRequest request) {
        return userProfileService.insert(userId, request);
    }

}
