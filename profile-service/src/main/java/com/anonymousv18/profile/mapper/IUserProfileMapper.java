package com.anonymousv18.profile.mapper;

import com.anonymousv18.profile.dto.request.UserProfileCreationRequest;
import com.anonymousv18.profile.dto.response.UserProfileResponse;
import com.anonymousv18.profile.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IUserProfileMapper {

    @Mapping(target = "id", ignore = true)
    UserProfileEntity toEntity(UserProfileCreationRequest userProfileCreationRequest);

    UserProfileResponse toUserProfileResponse(UserProfileEntity userProfileEntity);

}
