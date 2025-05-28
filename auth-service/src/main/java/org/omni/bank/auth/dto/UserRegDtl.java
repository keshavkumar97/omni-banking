package org.omni.bank.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegDtl {
    private String userName;
    private String password;
    private String role;
}
