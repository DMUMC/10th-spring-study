package com.umc.jaengchalttak.global.security.dto;

import com.umc.jaengchalttak.domain.user.enums.SocialProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO {

    private final String id;
    private final String email;
    private final String name;

    @Override
    public SocialProvider getSocialProvider() {
        return SocialProvider.KAKAO;
    }

    @Override
    public String getSocialUid() {
        return id;
    }

    @Override
    public String getSocialEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }

}
