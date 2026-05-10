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

@Component
@RequiredArgsConstructor
public class AppleOAuthClient implements OAuthClient {

    private static final String APPLE_KEYS_URL = "https://appleid.apple.com/auth/keys";

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Override
    @SuppressWarnings("unchecked")
    public SocialUserInfo getUserInfo(String idToken) {
        try {
            // 1. JWT 헤더에서 kid 추출 (어떤 공개키로 서명됐는지 식별)
            String[] parts = idToken.split("\\.");
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            Map<String, String> header = objectMapper.readValue(headerJson, Map.class);
            String kid = header.get("kid");

            // 2. Apple 공개키 목록 가져오기
            Map<String, Object> jwksResponse = restClient.get()
                    .uri(APPLE_KEYS_URL)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Map<String, Object>>() {});

            if (jwksResponse == null) throw new IllegalStateException("Failed to fetch Apple public keys");

            List<Map<String, String>> keys = (List<Map<String, String>>) jwksResponse.get("keys");

            // 3. kid가 일치하는 공개키 찾기
            Map<String, String> matchingKey = keys.stream()
                    .filter(k -> kid.equals(k.get("kid")))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No matching Apple public key for kid: " + kid));

            // 4. RSA 공개키 생성 (n: modulus, e: exponent)
            BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(matchingKey.get("n")));
            BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(matchingKey.get("e")));
            PublicKey publicKey = KeyFactory.getInstance("RSA")
                    .generatePublic(new RSAPublicKeySpec(modulus, exponent));

            // 5. id_token 서명 검증 및 claims 추출
            Claims claims = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(idToken)
                    .getPayload();

            String sub = claims.getSubject();
            String email = claims.get("email", String.class);

            // Apple은 최초 로그인 이후 id_token에 이름을 포함하지 않음
            return new SocialUserInfo(sub, email != null ? email : "", "");

        } catch (Exception e) {
            throw new IllegalStateException("Failed to verify Apple id_token: " + e.getMessage(), e);
        }
    }

    @Override
    public SocialProvider getProvider() {
        return SocialProvider.APPLE;
    }
}
