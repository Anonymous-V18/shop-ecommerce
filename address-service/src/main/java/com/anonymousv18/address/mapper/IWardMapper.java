package com.anonymousv18.address.mapper;

import com.anonymousv18.address.dto.response.WardResponse;
import com.anonymousv18.address.entity.WardEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IWardMapper {

    WardResponse toDTO(WardEntity wardEntity);

}
