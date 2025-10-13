package com.anonymousv18.address.service.impl;

import com.anonymousv18.address.dto.response.WardResponse;
import com.anonymousv18.address.mapper.IWardMapper;
import com.anonymousv18.address.repository.IWardRepository;
import com.anonymousv18.address.service.IWardService;
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
public class WardService implements IWardService {

    IWardRepository wardRepository;
    IWardMapper wardMapper;

    @Override
    public Set<WardResponse> findAllByDistrictId(String districtId) {
        return wardRepository.findAllByDistrictId(districtId).stream()
                .map(wardMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
