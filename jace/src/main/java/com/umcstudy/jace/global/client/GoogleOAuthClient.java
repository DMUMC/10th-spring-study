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
public class GoogleOAuthClient implements OAuthClient {

    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";
    private final RestClient restClient;

    @Override
    public SocialUserInfo getUserInfo(String accessToken) {
        Map<String, Object> body = restClient.get()
                .uri(USER_INFO_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});

        if (body == null) throw new IllegalStateException("Google API returned null body");

        String id = (String) body.get("sub");
        String email = (String) body.getOrDefault("email", "");
        String name = (String) body.getOrDefault("name", "");

        return new SocialUserInfo(id, email, name);
    }

    @Override
    public SocialProvider getProvider() {
        return SocialProvider.GOOGLE;
    }
}
