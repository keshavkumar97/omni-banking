package org.omni.bank.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String jwtToken;
    private String userId;
    private String userName;
    private String role;
}
