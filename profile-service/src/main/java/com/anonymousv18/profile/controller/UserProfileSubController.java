package com.anonymousv18.profile.controller;

import com.anonymousv18.profile.dto.request.UserProfileSubRequest;
import com.anonymousv18.profile.dto.response.ApiResponse;
import com.anonymousv18.profile.dto.response.UserProfileSubResponse;
import com.anonymousv18.profile.service.IUserProfileSubService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users/profiles")
public class UserProfileSubController {

    IUserProfileSubService userProfileSubService;

    @PostMapping("/{userProfileId}/profile-subs/insert")
    public ApiResponse<UserProfileSubResponse> insert(@PathVariable(name = "userProfileId") String userProfileId,
                                                      @RequestBody UserProfileSubRequest userProfileSubRequest) {
        UserProfileSubResponse response = userProfileSubService.insert(userProfileId, userProfileSubRequest);
        return ApiResponse.<UserProfileSubResponse>builder()
                .result(response)
                .build();
    }

    @PutMapping("/profile-subs/update/{id}")
    public ApiResponse<UserProfileSubResponse> update(@PathVariable(name = "id") String id,
                                                      @RequestBody UserProfileSubRequest userProfileSubRequest) {
        UserProfileSubResponse response = userProfileSubService.update(id, userProfileSubRequest);
        return ApiResponse.<UserProfileSubResponse>builder()
                .result(response)
                .build();
    }

    @DeleteMapping("/profile-subs/delete/{id}")
    public ApiResponse<String> delete(@PathVariable(name = "id") String id) {
        userProfileSubService.delete(id);
        return ApiResponse.<String>builder()
                .message("Delete sub profile successfully !")
                .build();
    }

    @GetMapping("/profile-subs/get")
    public ApiResponse<List<UserProfileSubResponse>> findByUserProfileId(@RequestParam(name = "userProfileId") String userProfileId) {
        List<UserProfileSubResponse> response = userProfileSubService.findByUserProfile_Id(userProfileId);
        return ApiResponse.<List<UserProfileSubResponse>>builder()
                .result(response)
                .build();
    }
}
