package com.anonymousv18.profile.repository.httpclient;

import com.anonymousv18.profile.configuration.AuthenticationRequestInterceptor;
import com.anonymousv18.profile.dto.request.AddressRequest;
import com.anonymousv18.profile.dto.response.AddressResponse;
import com.anonymousv18.profile.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "address-service", url = "${app.services.address}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface IAddressRepository {

    @PostMapping(value = "/internal/address/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<AddressResponse> insert(@RequestBody AddressRequest addressRequest);

    @PutMapping(value = "/internal/address/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<AddressResponse> update(
            @PathVariable(name = "id") String id,
            @RequestBody AddressRequest addressRequest);

    @DeleteMapping(value = "/internal/address/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable(name = "id") String id);

    @GetMapping(value = "/internal/address/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<AddressResponse> findById(@RequestParam("id") String id);
}
