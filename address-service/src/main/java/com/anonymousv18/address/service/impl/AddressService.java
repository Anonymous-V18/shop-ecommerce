package com.anonymousv18.address.service.impl;

import com.anonymousv18.address.dto.request.AddressRequest;
import com.anonymousv18.address.dto.response.AddressResponse;
import com.anonymousv18.address.entity.AddressEntity;
import com.anonymousv18.address.entity.WardEntity;
import com.anonymousv18.address.exception.AppException;
import com.anonymousv18.address.exception.ErrorCode;
import com.anonymousv18.address.mapper.IAddressMapper;
import com.anonymousv18.address.repository.IAddressRepository;
import com.anonymousv18.address.repository.IWardRepository;
import com.anonymousv18.address.service.IAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AddressService implements IAddressService {

    IAddressRepository addressRepository;
    IWardRepository wardRepository;
    IAddressMapper addressMapper;

    @Override
    public AddressResponse insert(AddressRequest addressRequest) {
        WardEntity wardEntity = wardRepository.findById(addressRequest.getWardId())
                .orElseThrow(() -> new AppException(ErrorCode.WARD_NOT_FOUND));
        if (addressRequest.getOther().isBlank()) {
            throw new AppException(ErrorCode.ADDRESS_OTHER_INVALID);
        }
        AddressEntity addressEntity = AddressEntity.builder()
                .ward(wardEntity)
                .other(addressRequest.getOther().trim())
                .build();
        addressEntity = addressRepository.save(addressEntity);
        return addressMapper.toDTO(addressEntity);
    }

    @Override
    public AddressResponse update(String id, AddressRequest addressRequest) {
        AddressEntity addressEntity = addressRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
        WardEntity wardEntity = wardRepository.findById(addressRequest.getWardId())
                .orElseThrow(() -> new AppException(ErrorCode.WARD_NOT_FOUND));
        if (addressRequest.getOther().isBlank()) {
            throw new AppException(ErrorCode.ADDRESS_OTHER_INVALID);
        }
        addressEntity.setWard(wardEntity);
        addressEntity.setOther(addressRequest.getOther().trim());
        addressEntity = addressRepository.save(addressEntity);
        return addressMapper.toDTO(addressEntity);
    }

    @Override
    public void delete(String id) {
        addressRepository.deleteById(id);
    }

    @Override
    public AddressResponse findById(String id) {
        AddressEntity addressEntity = addressRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
        return addressMapper.toDTO(addressEntity);
    }
}
