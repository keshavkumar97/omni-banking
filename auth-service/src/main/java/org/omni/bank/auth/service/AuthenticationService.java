package org.omni.bank.auth.service;

import org.omni.bank.auth.dto.LoginRequest;
import org.omni.bank.auth.dto.LoginResponse;
import org.omni.bank.auth.dto.UserRegDtl;
import org.omni.bank.auth.model.Users;

import java.util.Optional;

public interface AuthenticationService {
    Optional<Users> registerUser(UserRegDtl userRegDtl);

    LoginResponse login(LoginRequest loginRequest);
}
