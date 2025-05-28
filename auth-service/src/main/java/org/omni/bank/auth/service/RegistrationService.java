package org.omni.bank.auth.service;

import org.omni.bank.auth.dto.UserRegDtl;
import org.omni.bank.auth.model.Users;

import java.util.Optional;

public interface RegistrationService {
    Optional<Users> registerUser(UserRegDtl userRegDtl);
}
