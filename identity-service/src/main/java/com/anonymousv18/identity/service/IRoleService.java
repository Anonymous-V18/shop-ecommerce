package com.anonymousv18.identity.service;

import com.anonymousv18.identity.dto.RoleDTO;
import com.anonymousv18.identity.dto.request.RoleRequest;

import java.util.List;

public interface IRoleService {
    RoleDTO insert(RoleRequest request);

    void delete(String[] ids);

    List<RoleDTO> showAll();
}
