package com.anonymousv18.identity.dto.request;

import com.anonymousv18.identity.validator.PasswordConstraint;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Size(min = 8, message = "USERNAME_INVALID")
    private String username;
    @PasswordConstraint(min = 8, message = "PASSWORD_INVALID")
    private String password;

}
