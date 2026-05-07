package com.umcstudy.jace.global.client;

import com.umcstudy.jace.domain.user.enums.SocialProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final RestTemplate restTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public SocialUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        ResponseEntity<Map> response = restTemplate.exchange(
                USER_INFO_URL, HttpMethod.GET, new HttpEntity<>(headers), Map.class);

        Map<String, Object> body = response.getBody();
        String id = String.valueOf(body.get("id"));
        Map<String, Object> kakaoAccount = (Map<String, Object>) body.get("kakao_account");
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
