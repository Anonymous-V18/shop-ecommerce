package com.anonymousv18.profile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistrictResponse {

    String id;
    String name;
    String fullName;
    String type;
    ProvinceResponse province;

}
