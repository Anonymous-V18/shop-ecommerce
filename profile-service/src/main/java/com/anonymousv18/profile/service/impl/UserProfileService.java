package com.anonymousv18.profile.service.impl;

import com.anonymousv18.profile.dto.request.UserProfileRequest;
import com.anonymousv18.profile.dto.response.UserProfileResponse;
import com.anonymousv18.profile.entity.UserProfileEntity;
import com.anonymousv18.profile.exception.AppException;
import com.anonymousv18.profile.exception.ErrorCode;
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
    public UserProfileResponse insert(String userId, UserProfileRequest userProfileRequest) {
        UserProfileEntity userProfileEntity = userProfileMapper.toEntity(userProfileRequest);
        userProfileEntity.setUserId(userId);
        userProfileEntity = userProfileRepository.save(userProfileEntity);
        return userProfileMapper.toUserProfileResponse(userProfileEntity);
    }

    @Override
    public UserProfileResponse update(String id, UserProfileRequest userProfileRequest) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_NOT_FOUND));
        userProfileEntity = userProfileMapper.toEntity(userProfileEntity, userProfileRequest);
        return userProfileMapper.toUserProfileResponse(userProfileEntity);
    }

    @Override
    public List<UserProfileResponse> getAll() {
        return userProfileRepository.findAll().stream()
                .map(userProfileMapper::toUserProfileResponse).toList();
    }

    @Override
    public UserProfileResponse findById(String profileId) {
        return userProfileRepository.findById(profileId)
                .map(userProfileMapper::toUserProfileResponse)
                .orElseThrow(() -> new RuntimeException("Not found user !"));
    }

}
