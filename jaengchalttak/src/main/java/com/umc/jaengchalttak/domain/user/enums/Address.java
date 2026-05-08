package com.umc.jaengchalttak.domain.user.enums;

import lombok.Getter;

@Getter
public enum Address {
    ANAM_DONG("서울특별시 성북구 안암동"),
    CHANG_DONG("서울특별시 도봉구 창동"),
    TEHERAN_RO("서울특별시 강남구 테헤란로");

    private final String value;

    Address(String value) {
        this.value = value;
    }

}