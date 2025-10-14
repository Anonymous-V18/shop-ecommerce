package com.anonymousv18.identity.mapper;

import com.anonymousv18.identity.dto.request.SignupRequest;
import com.anonymousv18.identity.dto.request.UserProfileRequest;
import org.mapstruct.Mapper;

@Mapper
public interface IProfileMapper {

    UserProfileRequest toProfileCreationRequest(SignupRequest signupRequest);
}
