package com.anonymousv18.address.mapper;

import com.anonymousv18.address.dto.response.DistrictResponse;
import com.anonymousv18.address.entity.DistrictEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IDistrictMapper {

    DistrictResponse toDTO(DistrictEntity districtEntity);

}
