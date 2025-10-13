package com.anonymousv18.address.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "province")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProvinceEntity extends BaseEntity {

    @OneToMany(mappedBy = "province")
    Set<DistrictEntity> districts = new HashSet<>();
}