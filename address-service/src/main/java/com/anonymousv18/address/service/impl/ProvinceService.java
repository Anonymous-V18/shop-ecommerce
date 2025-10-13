package com.anonymousv18.address.service.impl;

import com.anonymousv18.address.dto.response.ProvinceResponse;
import com.anonymousv18.address.mapper.IProvinceMapper;
import com.anonymousv18.address.repository.IProvinceRepository;
import com.anonymousv18.address.service.IProvinceService;
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
public class ProvinceService implements IProvinceService {

    IProvinceRepository provinceRepository;
    IProvinceMapper provinceMapper;


    @Override
    public Set<ProvinceResponse> findAll() {
        return provinceRepository.findAll().stream()
                .map(provinceMapper::toDTO)
                .collect(Collectors.toSet());
    }

}
