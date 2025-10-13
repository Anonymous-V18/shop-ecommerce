package com.anonymousv18.profile.service;

import com.anonymousv18.profile.dto.request.UserProfileCreationRequest;
import com.anonymousv18.profile.dto.response.UserProfileResponse;

import java.util.List;

public interface IUserProfileService {

    UserProfileResponse createUserProfile(String userId, UserProfileCreationRequest userProfileCreationRequest);

    List<UserProfileResponse> getAllUserProfile();

    UserProfileResponse findUserProfile(String profileId);

}
