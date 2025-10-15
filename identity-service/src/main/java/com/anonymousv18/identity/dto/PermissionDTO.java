package com.anonymousv18.identity.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionDTO {

    String id;
    String createdBy;
    Date createdDate;
    String modifiedBy;
    Date modifiedDate;
    String name;
    String description;

}
