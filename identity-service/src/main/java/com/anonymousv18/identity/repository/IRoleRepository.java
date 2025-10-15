package com.anonymousv18.identity.repository;

import com.anonymousv18.identity.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findOneByCode(String code);

    boolean existsByCode(String code);
}
