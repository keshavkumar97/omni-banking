package org.omni.bank.auth.service.impl;

import com.bank.common.RoleEnum;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.omni.bank.auth.config.JwtTokenProvider;
import org.omni.bank.auth.dto.LoginRequest;
import org.omni.bank.auth.dto.LoginResponse;
import org.omni.bank.auth.dto.RegisterUserRequest;
import org.omni.bank.auth.exception.DuplicateEntryException;
import org.omni.bank.auth.model.Users;
import org.omni.bank.auth.repositories.UserRepo;
import org.omni.bank.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepo;
    private final JwtTokenProvider tokenProvider;


    public AuthenticationServiceImpl(@Autowired UserRepo userRepo,
                                     @Autowired JwtTokenProvider tokenProvider) {
        this.userRepo = userRepo;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Optional<Users> registerUser(RegisterUserRequest registerUserRequest) {
        if (userRepo.existsByUserName(registerUserRequest.getUserName())) {
            throw new DuplicateEntryException(HttpStatus.CONFLICT, "User " +
                    "already exits");
        }
        if (registerUserRequest == null || StringUtils.isBlank(registerUserRequest.getUserName())
                || StringUtils.isBlank(registerUserRequest.getPassword())) {
            throw new IllegalArgumentException("User Registration detail " +
                    "cannot be null or empty");
        }
        log.info("Assigning default User role: " + RoleEnum.USER);

        Users user = new Users();
        user.setUserName(registerUserRequest.getUserName());
        user.setPassword(registerUserRequest.getPassword());
        user.setRole(RoleEnum.USER);
        Users savedUser = new Users();
        try {
            savedUser = userRepo.save(user);
        } catch (Exception ex) {
            log.error("Error while registering user");
        }
        return Optional.of(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Optional<Users> user =
                userRepo.findByUserName(loginRequest.getUserName());
        LoginResponse loginResponse = new LoginResponse();
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {

            loginResponse.setJwtToken(tokenProvider.generateToken(user.get()));
            loginResponse.setUserId(user.get().getUserId());
            loginResponse.setRole(user.get().getRole());
            loginResponse.setUserName(user.get().getUsername());
            return loginResponse;
        }

        return loginResponse;
    }
}
