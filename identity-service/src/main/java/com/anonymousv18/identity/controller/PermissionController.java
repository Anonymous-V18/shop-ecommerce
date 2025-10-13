package com.anonymousv18.identity.controller;

import com.anonymousv18.identity.dto.PermissionDTO;
import com.anonymousv18.identity.dto.response.ApiResponse;
import com.anonymousv18.identity.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permissions")
public class PermissionController {

    IPermissionService permissionService;

    @PostMapping("/insert")
    public ApiResponse<PermissionDTO> insert(@RequestBody PermissionDTO request) {
        PermissionDTO response = permissionService.insert(request);
        return ApiResponse.<PermissionDTO>builder().result(response).build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> delete(@RequestBody String[] nameList) {
        permissionService.delete(nameList);
        return ApiResponse.<String>builder().message("Xóa thành công !").build();
    }

    @GetMapping("/showAll")
    public ApiResponse<List<PermissionDTO>> showAll() {
        List<PermissionDTO> response = permissionService.showAll();
        return ApiResponse.<List<PermissionDTO>>builder().result(response).build();
    }

}
