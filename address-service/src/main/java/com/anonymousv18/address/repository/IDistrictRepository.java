package com.anonymousv18.address.repository;

import com.anonymousv18.address.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IDistrictRepository extends JpaRepository<DistrictEntity, String> {

    Set<DistrictEntity> findAllByProvinceId(String provinceId);
}
