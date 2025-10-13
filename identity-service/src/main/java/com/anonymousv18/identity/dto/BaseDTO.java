package com.anonymousv18.identity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseDTO {

    String id;
    String createdBy;
    Date createdDate;
    String modifiedBy;
    Date modifiedDate;

}
