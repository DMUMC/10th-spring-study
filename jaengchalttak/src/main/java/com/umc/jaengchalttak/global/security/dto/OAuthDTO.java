package com.umc.jaengchalttak.global.security.dto;

import com.umc.jaengchalttak.domain.user.enums.SocialProvider;

public interface OAuthDTO {
    SocialProvider getSocialProvider();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
