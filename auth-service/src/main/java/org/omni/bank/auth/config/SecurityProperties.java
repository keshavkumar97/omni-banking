package org.omni.bank.auth.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityProperties {
    private final List<String> publicUrl = List.of("/auth/usr/register", "/auth/usr" +
            "/login");

    public List<String> getPublicUrl() {
        return publicUrl;
    }
}
