package com.anonymousv18.address.repository;

import com.anonymousv18.address.entity.WardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IWardRepository extends JpaRepository<WardEntity, String> {

    Set<WardEntity> findAllByDistrictId(String districtId);
}
