package com.anonymousv18.identity.service.iml;

import com.anonymousv18.identity.dto.RoleDTO;
import com.anonymousv18.identity.dto.request.RoleRequest;
import com.anonymousv18.identity.entity.PermissionEntity;
import com.anonymousv18.identity.entity.RoleEntity;
import com.anonymousv18.identity.exception.AppException;
import com.anonymousv18.identity.exception.ErrorCode;
import com.anonymousv18.identity.mapper.IRoleMapper;
import com.anonymousv18.identity.repository.IPermissionRepository;
import com.anonymousv18.identity.repository.IRoleRepository;
import com.anonymousv18.identity.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {

    IRoleMapper roleMapper;
    IRoleRepository roleRepository;
    IPermissionRepository permissionRepository;

    @Override
    public RoleDTO insert(RoleRequest request) {
        RoleEntity roleEntity = roleMapper.toEntity(request);

        List<PermissionEntity> permissions = permissionRepository.findAllById(request.getPermissionNameList());
        if (permissions.contains(null)) {
            throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        }
        RoleEntity finalRoleEntity = roleEntity;
        permissions.forEach(permissionEntity -> permissionEntity.getRoles().add(finalRoleEntity));
        roleEntity.setPermissions(new HashSet<>(permissions));

        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.toDTO(roleEntity);
    }

    @Override
    public void delete(String[] ids) {
        roleRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public List<RoleDTO> showAll() {
        return roleRepository.findAll().stream().map(roleMapper::toDTO).toList();
    }
}
