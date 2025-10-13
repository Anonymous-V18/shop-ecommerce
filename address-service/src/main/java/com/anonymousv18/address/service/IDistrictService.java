package com.anonymousv18.address.service;

import com.anonymousv18.address.dto.response.DistrictResponse;

import java.util.Set;

public interface IDistrictService {

    Set<DistrictResponse> findAllByProvinceId(String provinceId);

}
