package com.anonymousv18.profile.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node("user_profile_sub")
public class UserProfileSubEntity {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;
    @Relationship(type = "HAS_SUB_USERPROFILE", direction = Relationship.Direction.INCOMING)
    UserProfileEntity userProfile;
    String name;
    String phoneNumber;
    String addressId;

}
