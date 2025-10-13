package com.anonymousv18.address.service;

import com.anonymousv18.address.dto.response.ProvinceResponse;

import java.util.Set;

public interface IProvinceService {

    Set<ProvinceResponse> findAll();

}
