package com.UmcSpringStudy.jingjing2.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final long accessExpiration;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration}") long accessExpiration
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.accessExpiration = accessExpiration;
    }

    public String createAccessToken(String provider, String subjectId) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(subjectId)
                .claim("provider", provider)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(accessExpiration)))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token, HttpServletRequest request) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            request.setAttribute("exception", "INVALID_TOKEN");
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}