package com.anonymousv18.profile.service.impl;

import com.anonymousv18.profile.dto.request.UserProfileCreationRequest;
import com.anonymousv18.profile.dto.response.UserProfileResponse;
import com.anonymousv18.profile.entity.UserProfileEntity;
import com.anonymousv18.profile.mapper.IUserProfileMapper;
import com.anonymousv18.profile.repository.IUserProfileRepository;
import com.anonymousv18.profile.service.IUserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService implements IUserProfileService {

    IUserProfileRepository userProfileRepository;
    IUserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse createUserProfile(String userId, UserProfileCreationRequest userProfileCreationRequest) {
        UserProfileEntity userProfileEntity = userProfileMapper.toEntity(userProfileCreationRequest);
        userProfileEntity.setUserId(userId);
        userProfileEntity = userProfileRepository.save(userProfileEntity);
        return userProfileMapper.toUserProfileResponse(userProfileEntity);
    }

    @Override
    public List<UserProfileResponse> getAllUserProfile() {
        return userProfileRepository.findAll().stream()
                .map(userProfileMapper::toUserProfileResponse).toList();
    }

    @Override
    public UserProfileResponse findUserProfile(String profileId) {
        return userProfileRepository.findById(profileId)
                .map(userProfileMapper::toUserProfileResponse)
                .orElseThrow(() -> new RuntimeException("Not found user !"));
    }

}
