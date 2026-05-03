package com.umc.jaengchalttak.domain.user.enums;

import lombok.Getter;

@Getter
public enum Address {
    ANAM_DONG("안암동"),
    CHANG_DONG("창동");

    private final String value;

    Address(String value) {
        this.value = value;
    }

}