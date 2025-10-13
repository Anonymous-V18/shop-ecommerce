package com.anonymousv18.identity.repository;

import com.anonymousv18.identity.entity.InvalidatedTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvalidatedTokenRepository extends JpaRepository<InvalidatedTokenEntity, String> {

}
