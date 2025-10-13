package com.anonymousv18.address.service;

import com.anonymousv18.address.dto.request.AddressRequest;
import com.anonymousv18.address.dto.response.AddressResponse;

public interface IAddressService {

    AddressResponse insert(AddressRequest addressRequest);

    AddressResponse update(String id, AddressRequest addressRequest);

    void delete(String id);

    AddressResponse findById(String id);

}
