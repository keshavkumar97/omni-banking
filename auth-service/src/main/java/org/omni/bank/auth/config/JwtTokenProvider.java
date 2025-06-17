package org.omni.bank.auth.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.omni.bank.auth.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;

@Slf4j
@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final SecureDigestAlgorithm<SecretKey, ?> algorithm = Jwts.SIG.HS512;
//    @Value("${auth.key.secret}")
//    private String secret;

    public JwtTokenProvider(@Value("${auth.jwt.secret-key}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Users user) {

        Instant now = Instant.now();
        Instant expiry = now.plusMillis(1000);

        return Jwts.builder()
                .subject(user.getUsername())
                .claims().add("role", user.getRole())
                .and()
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(key, algorithm)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException expJwt) {
            log.error("Token Expired");

        } catch (IllegalArgumentException | JwtException e) {
            log.error("Invalid JWT token");
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
