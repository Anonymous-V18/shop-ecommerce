package com.anonymousv18.profile.mapper;

import com.anonymousv18.profile.dto.request.UserProfileRequest;
import com.anonymousv18.profile.dto.response.UserProfileResponse;
import com.anonymousv18.profile.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface IUserProfileMapper {

    @Mapping(target = "id", ignore = true)
    UserProfileEntity toEntity(UserProfileRequest userProfileRequest);

    UserProfileResponse toUserProfileResponse(UserProfileEntity userProfileEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "subProfiles", ignore = true)
    UserProfileEntity toEntity(@MappingTarget UserProfileEntity oldUserProfile, UserProfileRequest newUserProfile);
}
