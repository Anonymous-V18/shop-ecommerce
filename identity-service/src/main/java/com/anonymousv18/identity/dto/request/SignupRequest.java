package com.anonymousv18.identity.dto.request;

import com.anonymousv18.identity.validator.PasswordConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {


    @Size(min = 8, message = "USERNAME_INVALID")
    String username;
    @PasswordConstraint(min = 8, message = "PASSWORD_INVALID")
    String password;
    String role;
    String email;
    String firstName;
    String lastName;
    LocalDate dob;
    Boolean gender;
    String phoneNumber;

}
