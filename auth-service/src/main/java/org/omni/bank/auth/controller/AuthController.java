package org.omni.bank.auth.controller;

import org.omni.bank.auth.dto.LoginRequest;
import org.omni.bank.auth.dto.LoginResponse;
import org.omni.bank.auth.dto.RegisterUserRequest;
import org.omni.bank.auth.model.Users;
import org.omni.bank.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/usr")
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(@Autowired AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Users> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        var addedUser = authService.registerUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser.orElse(null));
    }

    @PostMapping(path="/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
//        authService.login(loginRequest);
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
