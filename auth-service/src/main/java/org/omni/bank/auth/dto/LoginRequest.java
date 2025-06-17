package org.omni.bank.auth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

//using Builder patter here
@Getter
public class LoginRequest {

    private final String userName;
    private final String password;

    @JsonCreator // Tells Jackson to use this constructor for deserialization
    // . basically need when we transfer the data like in @RequestBody
    public LoginRequest(@JsonProperty("userName") String userName, // Map JSON "userName" to this parameter
                        @JsonProperty("password") String password) { // Map JSON "password" to this parameter
        // Basic validation for direct JSON creation (optional, but good practice)
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }
        this.userName = userName;
        this.password = password;
    }

    private LoginRequest(LoginRequestBuilder builder) {
        if (builder.userName == null || builder.userName.isBlank())
            throw new IllegalArgumentException("Username cannot be null or " +
                    "empty");
        if (builder.password == null || builder.password.isBlank())
            throw new IllegalArgumentException("Password cannot be null or " +
                    "empty");
        this.userName = builder.userName;
        this.password = builder.password;
    }

    public static class LoginRequestBuilder {
        private String userName;
        private String password;

        private LoginRequestBuilder() {

        }

        public LoginRequestBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public LoginRequestBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(this);
        }
    }
}
