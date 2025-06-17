package org.omni.bank.auth.dto;

import com.bank.common.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String jwtToken;
    private long userId;
    private String userName;
    private RoleEnum role;
}
