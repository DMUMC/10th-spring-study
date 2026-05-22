package com.umcstudy.jace.global.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.umcstudy.jace.domain.user.enums.SocialProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    private final RestClient restClient;

    @Override
    public SocialUserInfo getUserInfo(String accessToken) {
        KakaoUserInfoResponse response = restClient.get()
                .uri(USER_INFO_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .body(KakaoUserInfoResponse.class);

        if (response == null) throw new IllegalStateException("Kakao API returned null body");

        String id = String.valueOf(response.id());
        String email = Optional.ofNullable(response.kakaoAccount())
                .map(KakaoAccount::email)
                .orElse("");
        String name = Optional.ofNullable(response.kakaoAccount())
                .map(KakaoAccount::profile)
                .map(KakaoProfile::nickname)
                .orElse("");

        return new SocialUserInfo(id, email, name);
    }

    @Override
    public SocialProvider getProvider() {
        return SocialProvider.KAKAO;
    }

    private record KakaoUserInfoResponse(
            Long id,
            @JsonProperty("kakao_account") KakaoAccount kakaoAccount
    ) {}

    private record KakaoAccount(
            String email,
            KakaoProfile profile
    ) {}

    private record KakaoProfile(
            String nickname
    ) {}
}
