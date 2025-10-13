package com.anonymousv18.identity.mapper;

import com.anonymousv18.identity.dto.PermissionDTO;
import com.anonymousv18.identity.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IPermissionMapper {

    PermissionDTO toDTO(PermissionEntity permissionEntity);

    @Mapping(target = "roles", ignore = true)
    PermissionEntity toEntity(PermissionDTO permissionDTO);
}
