package com.umcstudy.jace.global.client;

import com.umcstudy.jace.domain.user.enums.SocialProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final RestClient restClient;

    @Override
    @SuppressWarnings("unchecked")
    public SocialUserInfo getUserInfo(String accessToken) {
        Map<String, Object> body = restClient.get()
                .uri(USER_INFO_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});

        if (body == null) throw new IllegalStateException("Kakao API returned null body");

        String id = String.valueOf(body.get("id"));
        Map<String, Object> kakaoAccount = (Map<String, Object>) body.getOrDefault("kakao_account", Map.of());
        String email = (String) kakaoAccount.getOrDefault("email", "");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String name = profile != null ? (String) profile.getOrDefault("nickname", "") : "";

        return new SocialUserInfo(id, email, name);
    }

    @Override
    public SocialProvider getProvider() {
        return SocialProvider.KAKAO;
    }
}
