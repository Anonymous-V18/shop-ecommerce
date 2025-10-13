package com.anonymousv18.identity.repository;

import com.anonymousv18.identity.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, String> {

}
