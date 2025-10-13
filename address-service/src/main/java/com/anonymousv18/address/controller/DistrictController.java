package com.anonymousv18.address.controller;

import com.anonymousv18.address.dto.response.ApiResponse;
import com.anonymousv18.address.dto.response.DistrictResponse;
import com.anonymousv18.address.service.IDistrictService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/districts")
public class DistrictController {

    IDistrictService districtService;

    @GetMapping("/get-all")
    public ApiResponse<Set<DistrictResponse>> findAllByProvinceId(
            @RequestParam(name = "province-id") String provinceId
    ) {
        Set<DistrictResponse> response = districtService.findAllByProvinceId(provinceId);
        return ApiResponse.<Set<DistrictResponse>>builder().result(response).build();
    }

}
