package org.omni.bank.auth.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.omni.bank.auth.dto.UserRegDtl;
import org.omni.bank.auth.exception.DuplicateEntryException;
import org.omni.bank.auth.model.Users;
import org.omni.bank.auth.repositories.RoleRepo;
import org.omni.bank.auth.repositories.UserRepo;
import org.omni.bank.auth.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;


    public RegistrationServiceImpl(@Autowired UserRepo userRepo,
                                   @Autowired RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public Optional<Users> registerUser(UserRegDtl userRegDtl) {
        if (userRepo.existsByUserName(userRegDtl.getUserName())) {
            throw new DuplicateEntryException(HttpStatus.CONFLICT, "User " +
                    "already exits");
        }
        if (userRegDtl == null || StringUtils.isBlank(userRegDtl.getUserName())
                || StringUtils.isBlank(userRegDtl.getPassword())
                || StringUtils.isBlank(userRegDtl.getRole())) {
            throw new IllegalArgumentException("User Registration detail " +
                    "cannot be null or empty");
        }
        log.info("role name = " + userRegDtl.getRole());
        var code = roleRepo.findRoleCodeByName(userRegDtl.getRole());
        log.info("role code "+code);
        if (StringUtils.isBlank(code))
            throw new NullPointerException("No match found for provided role " +
                    "name");

        Users user = new Users();
        user.setUserName(userRegDtl.getUserName());
        user.setPassword(userRegDtl.getPassword());
        user.setRole(code);
        Users savedUser = new Users();
        try {
            savedUser = userRepo.save(user);
        } catch (Exception ex) {
            log.error("Error while registering user");
        }
        return Optional.of(savedUser);
    }
}
