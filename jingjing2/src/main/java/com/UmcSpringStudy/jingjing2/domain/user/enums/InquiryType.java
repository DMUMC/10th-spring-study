package com.UmcSpringStudy.jingjing2.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryType {
    SYSTEM_ERROR,
    PAYMENT_REWARD,
    STORE_INFO,
    MEMBER_INFO,
    ETC
}