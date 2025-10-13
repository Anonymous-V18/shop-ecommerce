package com.anonymousv18.address.repository;

import com.anonymousv18.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<AddressEntity, String> {
}
