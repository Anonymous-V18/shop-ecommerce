package com.anonymousv18.identity.mapper;

import com.anonymousv18.identity.dto.RoleDTO;
import com.anonymousv18.identity.dto.request.RoleRequest;
import com.anonymousv18.identity.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IRoleMapper {

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    RoleEntity toEntity(RoleRequest request);

    RoleDTO toDTO(RoleEntity roleEntity);
}
