package com.UmcSpringStudy.jingjing2.domain.user.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSettingsResponse {
    private Boolean eventNotice;
    private Boolean reviewNotice;
    private Boolean iqNotice;
    private Boolean adAllow;
    private Boolean locAllow;
}