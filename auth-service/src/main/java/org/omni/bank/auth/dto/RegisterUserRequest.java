package org.omni.bank.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean phoneVerified;
    private boolean emailVerified;
}
