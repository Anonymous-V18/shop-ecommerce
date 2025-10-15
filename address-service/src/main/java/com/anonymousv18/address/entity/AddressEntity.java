package com.anonymousv18.address.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "address")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @CreatedBy
    String createdBy;
    @CreatedDate
    Date createdDate;
    @LastModifiedBy
    String modifiedBy;
    @LastModifiedDate
    Date modifiedDate;
    
    String other;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    WardEntity ward;
}
