package com.anonymousv18.identity.service;

import com.anonymousv18.identity.dto.PermissionDTO;

import java.util.List;

public interface IPermissionService {

    PermissionDTO insert(PermissionDTO request);

    void delete(String[] nameList);

    List<PermissionDTO> showAll();

}
