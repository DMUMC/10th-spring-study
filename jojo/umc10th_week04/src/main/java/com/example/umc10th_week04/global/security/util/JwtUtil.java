package com.example.umc10th_week04.global.security.util;

import com.example.umc10th_week04.domain.user.enums.SocialType;
import com.example.umc10th_week04.global.security.entity.AuthUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // AccessToken 생성
    public String createAccessToken(
            Long userId,
            String name,
            String email,
            SocialType socialType,
            String socialUid,
            Collection<? extends GrantedAuthority> authorities
    ) {
        return createToken(
                userId,
                name,
                email,
                socialType,
                socialUid,
                authorities,
                accessExpiration
        );
    }

    public Claims validateAndGetClaims(String token) {
        if (token == null || token.isBlank()) {
            throw new JwtException("JWT token is empty");
        }

        Claims claims = getClaims(token).getPayload();
        validateRequiredClaims(claims);
        return claims;
    }

    public Long getUserId(Claims claims) {
        return Long.valueOf(claims.getSubject());
    }

    public SocialType getSocialType(Claims claims) {
        String socialType = claims.get("social_type", String.class);
        return socialType == null ? null : SocialType.valueOf(socialType);
    }

    public String getSocialUid(Claims claims) {
        return claims.get("social_uid", String.class);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
        String roles = claims.get("role", String.class);
        if (roles == null || roles.isBlank()) {
            return List.of();
        }
        return Arrays.stream(roles.split(","))
                .map(String::trim)
                .filter(role -> !role.isBlank())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private void validateRequiredClaims(Claims claims) {
        String subject = claims.getSubject();
        String name = claims.get("name", String.class);
        String email = claims.get("email", String.class);
        String role = claims.get("role", String.class);

        if (subject == null || subject.isBlank()) {
            throw new JwtException("JWT subject is missing");
        }

        try {
            Long.parseLong(subject);
        } catch (NumberFormatException e) {
            throw new JwtException("JWT subject is invalid", e);
        }

        if (name == null || name.isBlank()) {
            throw new JwtException("JWT name claim is missing");
        }

        if (email == null || email.isBlank()) {
            throw new JwtException("JWT email claim is missing");
        }

        if (role == null || role.isBlank()) {
            throw new JwtException("JWT role claim is missing");
        }
    }

    // 토큰 생성
    private String createToken(
            Long userId,
            String name,
            String email,
            SocialType socialType,
            String socialUid,
            Collection<? extends GrantedAuthority> authorities,
            Duration expiration
    ) {
        Instant now = Instant.now();

        // 인가 정보
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        JwtBuilder builder = Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("name", name)
                .claim("email", email)
                .claim("role", roles)
                .issuedAt(Date.from(now)) // 언제 발급한지
                .expiration(Date.from(now.plus(expiration))) // 언제까지 유효한지
                .signWith(secretKey);

        if (socialType != null) {
            builder.claim("social_type", socialType.name());
        }

        if (socialUid != null) {
            builder.claim("social_uid", socialUid);
        }

        return builder.compact();
    }

    // 토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }

}
