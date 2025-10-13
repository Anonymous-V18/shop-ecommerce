package com.anonymousv18.address.controller;

import com.anonymousv18.address.dto.response.ApiResponse;
import com.anonymousv18.address.dto.response.ProvinceResponse;
import com.anonymousv18.address.service.IProvinceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/provinces")
public class ProvinceController {

    IProvinceService provinceService;

    @GetMapping("/get-all")
    public ApiResponse<Set<ProvinceResponse>> findAll() {
        Set<ProvinceResponse> response = provinceService.findAll();
        return ApiResponse.<Set<ProvinceResponse>>builder().result(response).build();
    }

}
