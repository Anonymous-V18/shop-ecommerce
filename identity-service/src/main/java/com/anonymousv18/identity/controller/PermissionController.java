package com.anonymousv18.identity.controller;

import com.anonymousv18.identity.dto.PermissionDTO;
import com.anonymousv18.identity.dto.response.ApiResponse;
import com.anonymousv18.identity.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permissions")
public class PermissionController {

    IPermissionService permissionService;

    @PostMapping("/insert")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<PermissionDTO> insert(@RequestBody PermissionDTO request) {
        PermissionDTO response = permissionService.insert(request);
        return ApiResponse.<PermissionDTO>builder().result(response).build();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> delete(@RequestBody String[] nameList) {
        permissionService.delete(nameList);
        return ApiResponse.<String>builder().message("Delete successfully !").build();
    }

    @GetMapping("/get-all")
    public ApiResponse<List<PermissionDTO>> showAll() {
        List<PermissionDTO> response = permissionService.showAll();
        return ApiResponse.<List<PermissionDTO>>builder().result(response).build();
    }

}
