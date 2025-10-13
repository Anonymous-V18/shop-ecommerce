package com.anonymousv18.profile.controller;

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

    @GetMapping("/{profileId}")
    @PreAuthorize("hasAnyAuthority('CREATE_POSTS','UPDATE_POSTS')")
    public UserProfileResponse getUserProfile(@PathVariable("profileId") String profileId) {
        return userProfileService.findUserProfile(profileId);
    }

    @GetMapping("/showAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllUserProfiles() {
        return userProfileService.getAllUserProfile();
    }
}
