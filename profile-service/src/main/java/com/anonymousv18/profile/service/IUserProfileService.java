package com.anonymousv18.profile.service;

import com.anonymousv18.profile.dto.request.UserProfileRequest;
import com.anonymousv18.profile.dto.response.UserProfileResponse;

import java.util.List;

public interface IUserProfileService {

    UserProfileResponse insert(String userId, UserProfileRequest userProfileRequest);

    UserProfileResponse update(String id, UserProfileRequest userProfileRequest);

    List<UserProfileResponse> getAll();

    UserProfileResponse findById(String id);

}
