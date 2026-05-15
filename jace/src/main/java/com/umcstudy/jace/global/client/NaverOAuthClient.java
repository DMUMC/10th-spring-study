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
public class NaverOAuthClient implements OAuthClient {

    private static final String USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";
    private final RestTemplate restTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public SocialUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        ResponseEntity<Map> response = restTemplate.exchange(
                USER_INFO_URL, HttpMethod.GET, new HttpEntity<>(headers), Map.class);

        Map<String, Object> body = response.getBody();
        Map<String, Object> responseData = (Map<String, Object>) body.get("response");
        String id = (String) responseData.get("id");
        String email = (String) responseData.getOrDefault("email", "");
        String name = (String) responseData.getOrDefault("name", "");

        return new SocialUserInfo(id, email, name);
    }

    @Override
    public SocialProvider getProvider() {
        return SocialProvider.NAVER;
    }
}
