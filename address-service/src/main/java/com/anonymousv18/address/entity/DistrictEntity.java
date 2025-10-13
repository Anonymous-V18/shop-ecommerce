package com.anonymousv18.address.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "district")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistrictEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "province_id")
    ProvinceEntity province;

    @OneToMany(mappedBy = "district")
    Set<WardEntity> wards = new HashSet<>();
}