package com.umcstudy.jace.global.client;

public record SocialUserInfo(
        String providerUserId,
        String email,
        String name
) {}
