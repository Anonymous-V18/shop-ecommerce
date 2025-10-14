package com.anonymousv18.profile.service;

import com.anonymousv18.profile.dto.request.UserProfileSubRequest;
import com.anonymousv18.profile.dto.response.UserProfileSubResponse;

import java.util.List;

public interface IUserProfileSubService {

    UserProfileSubResponse insert(String userProfileId, UserProfileSubRequest userProfileSubRequest);

    UserProfileSubResponse update(String id, UserProfileSubRequest userProfileSubRequest);

    void delete(String id);

    List<UserProfileSubResponse> findByUserProfile_Id(String userProfileId);

}
