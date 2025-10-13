package com.anonymousv18.identity.mapper;

import com.anonymousv18.identity.dto.request.ProfileCreationRequest;
import com.anonymousv18.identity.dto.request.SignupRequest;
import org.mapstruct.Mapper;

@Mapper
public interface IProfileMapper {

    ProfileCreationRequest toProfileCreationRequest(SignupRequest signupRequest);
}
