package com.anonymousv18.address.mapper;

import com.anonymousv18.address.dto.response.AddressResponse;
import com.anonymousv18.address.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IAddressMapper {

    AddressResponse toDTO(AddressEntity addressEntity);

}
