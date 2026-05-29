package com.example.umc10th_week04.global.security.dto;

import com.example.umc10th_week04.domain.user.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
