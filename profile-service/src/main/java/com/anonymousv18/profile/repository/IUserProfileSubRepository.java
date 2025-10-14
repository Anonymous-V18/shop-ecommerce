package com.anonymousv18.profile.repository;

import com.anonymousv18.profile.entity.UserProfileSubEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserProfileSubRepository extends Neo4jRepository<UserProfileSubEntity, String> {
    List<UserProfileSubEntity> findByUserProfile_Id(String userProfileId);
}
