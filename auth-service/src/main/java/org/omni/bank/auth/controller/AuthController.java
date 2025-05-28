package org.omni.bank.auth.controller;

import org.omni.bank.auth.dto.UserRegDtl;
import org.omni.bank.auth.model.Users;
import org.omni.bank.auth.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService service;

    public AuthController(@Autowired RegistrationService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Users> registerUser(@RequestBody UserRegDtl userRegDtl) {
        var addedUser = service.registerUser(userRegDtl);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser.orElse(null));
    }
}
