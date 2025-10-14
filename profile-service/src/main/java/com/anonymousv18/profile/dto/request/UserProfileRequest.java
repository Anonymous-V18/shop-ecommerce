package com.anonymousv18.profile.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileRequest {

    String firstName;
    String lastName;
    LocalDate dob;
    String phoneNumber;
    Boolean gender;

}
