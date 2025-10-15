package com.anonymousv18.identity.controller;

import com.anonymousv18.identity.dto.RoleDTO;
import com.anonymousv18.identity.dto.request.RoleRequest;
import com.anonymousv18.identity.dto.response.ApiResponse;
import com.anonymousv18.identity.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles")
public class RoleController {

    IRoleService roleService;

    @PostMapping("/insert")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<RoleDTO> insert(@RequestBody RoleRequest request) {
        RoleDTO response = roleService.insert(request);
        return ApiResponse.<RoleDTO>builder().result(response).build();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> delete(@RequestBody String[] ids) {
        roleService.delete(ids);
        return ApiResponse.<String>builder().message("Delete successfully !").build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<RoleDTO>> showAll() {
        List<RoleDTO> response = roleService.showAll();
        return ApiResponse.<List<RoleDTO>>builder().result(response).build();
    }

}
