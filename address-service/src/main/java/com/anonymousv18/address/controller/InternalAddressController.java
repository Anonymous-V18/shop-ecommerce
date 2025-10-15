package com.anonymousv18.address.controller;

import com.anonymousv18.address.dto.request.AddressRequest;
import com.anonymousv18.address.dto.response.AddressResponse;
import com.anonymousv18.address.dto.response.ApiResponse;
import com.anonymousv18.address.service.IAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/internal")
public class InternalAddressController {

    IAddressService addressService;

    @PostMapping("/address/insert")
    public ApiResponse<AddressResponse> insert(@RequestBody AddressRequest addressRequest) {
        AddressResponse response = addressService.insert(addressRequest);
        return ApiResponse.<AddressResponse>builder()
                .result(response)
                .build();
    }

    @PutMapping("/address/update/{id}")
    public ApiResponse<AddressResponse> update(
            @PathVariable(name = "id") String id,
            @RequestBody AddressRequest addressRequest) {
        AddressResponse response = addressService.update(id, addressRequest);
        return ApiResponse.<AddressResponse>builder()
                .result(response)
                .build();
    }

    @DeleteMapping("/address/delete/{id}")
    public ApiResponse<String> delete(@PathVariable(name = "id") String id) {
        addressService.delete(id);
        return ApiResponse.<String>builder()
                .message("Delete address successfully !")
                .build();
    }

    @GetMapping("/address/get")
    public ApiResponse<AddressResponse> findById(@RequestParam("id") String id) {
        AddressResponse response = addressService.findById(id);
        return ApiResponse.<AddressResponse>builder()
                .result(response)
                .build();
    }
}
