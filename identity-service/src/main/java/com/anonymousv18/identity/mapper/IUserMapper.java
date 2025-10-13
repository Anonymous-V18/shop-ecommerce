package com.anonymousv18.identity.mapper;

import com.anonymousv18.identity.dto.UserDTO;
import com.anonymousv18.identity.dto.request.SignupRequest;
import com.anonymousv18.identity.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IUserMapper {

    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toEntity(SignupRequest signupRequest);

}
