package com.anonymousv18.identity.repository;

import com.anonymousv18.identity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findOneByUsername(String name);

    Boolean existsByUsernameOrEmail(String username, String email);

}
