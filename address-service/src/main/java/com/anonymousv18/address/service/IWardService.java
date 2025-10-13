package com.anonymousv18.address.service;

import com.anonymousv18.address.dto.response.WardResponse;

import java.util.Set;

public interface IWardService {

    Set<WardResponse> findAllByDistrictId(String districtId);

}
