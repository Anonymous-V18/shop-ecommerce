package com.anonymousv18.identity.service.iml;

import com.anonymousv18.identity.dto.PermissionDTO;
import com.anonymousv18.identity.entity.PermissionEntity;
import com.anonymousv18.identity.mapper.IPermissionMapper;
import com.anonymousv18.identity.repository.IPermissionRepository;
import com.anonymousv18.identity.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService implements IPermissionService {

    IPermissionMapper permissionMapper;
    IPermissionRepository permissionRepository;

    @Override
    public PermissionDTO insert(PermissionDTO request) {
        PermissionEntity permissionEntity = permissionMapper.toEntity(request);
        permissionEntity = permissionRepository.save(permissionEntity);
        return permissionMapper.toDTO(permissionEntity);
    }

    @Override
    public void delete(String[] nameList) {
        permissionRepository.deleteAllById(Arrays.asList(nameList));
    }

    @Override
    public List<PermissionDTO> showAll() {
        return permissionRepository.findAll().stream().map(permissionMapper::toDTO).toList();
    }

}
