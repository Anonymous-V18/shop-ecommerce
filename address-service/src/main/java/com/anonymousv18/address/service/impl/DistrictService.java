package com.anonymousv18.address.service.impl;

import com.anonymousv18.address.dto.response.DistrictResponse;
import com.anonymousv18.address.mapper.IDistrictMapper;
import com.anonymousv18.address.repository.IDistrictRepository;
import com.anonymousv18.address.service.IDistrictService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DistrictService implements IDistrictService {

    IDistrictRepository districtRepository;
    IDistrictMapper districtMapper;

    @Override
    public Set<DistrictResponse> findAllByProvinceId(String provinceId) {
        return districtRepository
                .findAllByProvinceId(provinceId).stream()
                .map(districtMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
