package com.anonymousv18.profile.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileSubRequest {

    String name;
    String phoneNumber;
    AddressRequest address;

}
