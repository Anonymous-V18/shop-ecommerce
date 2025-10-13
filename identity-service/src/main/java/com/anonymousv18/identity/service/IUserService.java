package com.anonymousv18.identity.service;

import com.anonymousv18.identity.dto.UserDTO;
import com.anonymousv18.identity.dto.request.SignupRequest;

import java.util.List;

public interface IUserService {

    UserDTO createUser(SignupRequest signupRequest);

    List<UserDTO> showAllUsers();

}
