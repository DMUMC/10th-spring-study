package com.UmcSpringStudy.jingjing2.domain.user.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
}