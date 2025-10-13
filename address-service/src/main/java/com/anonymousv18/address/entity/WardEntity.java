package com.anonymousv18.address.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ward")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WardEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "district_id")
    DistrictEntity district;

    @OneToMany(mappedBy = "ward")
    Set<AddressEntity> addresses = new HashSet<>();
}