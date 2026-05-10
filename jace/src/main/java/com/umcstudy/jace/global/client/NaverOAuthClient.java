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
public class NaverOAuthClient implements OAuthClient {

    private static final String USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";
    private final RestClient restClient;

    @Override
    @SuppressWarnings("unchecked")
    public SocialUserInfo getUserInfo(String accessToken) {
        Map<String, Object> body = restClient.get()
                .uri(USER_INFO_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});

        if (body == null) throw new IllegalStateException("Naver API returned null body");

        Map<String, Object> responseData = (Map<String, Object>) body.get("response");
        if (responseData == null) throw new IllegalStateException("Naver API response missing 'response' field");

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
