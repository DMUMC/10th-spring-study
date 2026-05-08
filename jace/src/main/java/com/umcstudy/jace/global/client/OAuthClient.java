package com.umcstudy.jace.global.client;

import com.umcstudy.jace.domain.user.enums.SocialProvider;

public interface OAuthClient {
    SocialUserInfo getUserInfo(String accessToken);
    SocialProvider getProvider();
}
