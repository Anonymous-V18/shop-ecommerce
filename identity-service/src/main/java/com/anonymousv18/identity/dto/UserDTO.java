package com.anonymousv18.identity.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends BaseDTO {

    String username;
    String password;
    Set<RoleDTO> roles = new HashSet<>();

}
