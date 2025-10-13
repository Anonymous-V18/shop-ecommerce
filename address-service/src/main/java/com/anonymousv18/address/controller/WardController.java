package com.anonymousv18.address.controller;

import com.anonymousv18.address.dto.response.ApiResponse;
import com.anonymousv18.address.dto.response.WardResponse;
import com.anonymousv18.address.service.IWardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/wards")
public class WardController {

    IWardService wardService;

    @GetMapping("/get-all")
    public ApiResponse<Set<WardResponse>> findAllByDistrictId(
            @RequestParam(name = "district-id") String districtId
    ) {
        Set<WardResponse> response = wardService.findAllByDistrictId(districtId);
        return ApiResponse.<Set<WardResponse>>builder().result(response).build();
    }

}
