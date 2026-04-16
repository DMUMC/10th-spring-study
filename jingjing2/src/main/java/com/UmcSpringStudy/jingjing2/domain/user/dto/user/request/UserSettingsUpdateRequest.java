package com.UmcSpringStudy.jingjing2.domain.user.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSettingsUpdateRequest {
    // 알림 설정 (Setting 엔티티)
    private Boolean eventNotice;
    private Boolean reviewNotice;
    private Boolean iqNotice;

    // 약관 동의 (Autorication 엔티티)
    private Boolean adAllow;
    private Boolean locAllow;
}