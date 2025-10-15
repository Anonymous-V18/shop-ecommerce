package com.anonymousv18.identity.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO {

    String id;
    String createdBy;
    Date createdDate;
    String modifiedBy;
    Date modifiedDate;
    String name;
    String code;

    List<PermissionDTO> permissions = new ArrayList<>();

}
