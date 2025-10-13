package com.anonymousv18.profile.repository;

import com.anonymousv18.profile.entity.UserProfileEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserProfileRepository extends Neo4jRepository<UserProfileEntity, String> {
}
