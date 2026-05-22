package com.umcstudy.jace.global.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umcstudy.jace.domain.user.enums.SocialProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class AppleOAuthClient implements OAuthClient {

    private static final String APPLE_KEYS_URL = "https://appleid.apple.com/auth/keys";

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    private final Map<String, PublicKey> publicKeyCache = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public SocialUserInfo getUserInfo(String idToken) {
        try {
            String[] parts = idToken.split("\\.");
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            Map<String, String> header = objectMapper.readValue(headerJson, Map.class);
            String kid = header.get("kid");

            PublicKey publicKey = publicKeyCache.computeIfAbsent(kid, k -> {
                refreshPublicKeyCache();
                return publicKeyCache.get(k);
            });

            if (publicKey == null) {
                throw new IllegalStateException("No matching Apple public key for kid: " + kid);
            }

            Claims claims = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(idToken)
                    .getPayload();

            String sub = claims.getSubject();
            String email = claims.get("email", String.class);

            return new SocialUserInfo(sub, email != null ? email : "", "");

        } catch (Exception e) {
            throw new IllegalStateException("Failed to verify Apple id_token: " + e.getMessage(), e);
        }
    }

    @Override
    public SocialProvider getProvider() {
        return SocialProvider.APPLE;
    }

    @SuppressWarnings("unchecked")
    private void refreshPublicKeyCache() {
        try {
            Map<String, Object> jwksResponse = restClient.get()
                    .uri(APPLE_KEYS_URL)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Map<String, Object>>() {});

            if (jwksResponse == null) throw new IllegalStateException("Failed to fetch Apple public keys");

            List<Map<String, String>> keys = (List<Map<String, String>>) jwksResponse.get("keys");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKeyCache.clear();
            for (Map<String, String> key : keys) {
                BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(key.get("n")));
                BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(key.get("e")));
                PublicKey publicKey = keyFactory.generatePublic(new RSAPublicKeySpec(modulus, exponent));
                publicKeyCache.put(key.get("kid"), publicKey);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to refresh Apple JWKS: " + e.getMessage(), e);
        }
    }
}
