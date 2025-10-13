package com.anonymousv18.address.repository;

import com.anonymousv18.address.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends JpaRepository<ProvinceEntity, String> {
}
