package com.anonymousv18.address.mapper;

import com.anonymousv18.address.dto.response.ProvinceResponse;
import com.anonymousv18.address.entity.ProvinceEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IProvinceMapper {

    ProvinceResponse toDTO(ProvinceEntity provinceEntity);

}
