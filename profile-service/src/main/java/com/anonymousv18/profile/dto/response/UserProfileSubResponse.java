package com.anonymousv18.profile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileSubResponse {

    String id;
    String name;
    String phoneNumber;
    AddressResponse address;

}
