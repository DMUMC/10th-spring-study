package com.umcstudy.jace.global.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String TOKEN_TYPE = "ACCESS";
    private static final String CLAIM_TYPE = "type";
    private static final String CLAIM_ROLE = "role";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMs;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateToken(Long userId) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim(CLAIM_TYPE, TOKEN_TYPE)
                .claim(CLAIM_ROLE, "USER")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUserId(String token) {
        return getClaims(token).getSubject();
    }

    public String getRole(String token) {
        return getClaims(token).get(CLAIM_ROLE, String.class);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return TOKEN_TYPE.equals(claims.get(CLAIM_TYPE, String.class));
        } catch (ExpiredJwtException e) {
            log.warn("JWT token expired");
        } catch (MalformedJwtException e) {
            log.warn("Malformed JWT token");
        } catch (SignatureException e) {
            log.warn("Invalid JWT signature");
        } catch (Exception e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
