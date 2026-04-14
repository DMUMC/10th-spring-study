package com.umc.jaengchalttak.domain.user.dto;

import com.umc.jaengchalttak.domain.user.enums.ServiceUseType;

import java.util.Date;
import java.util.Map;

public record UserInfoDTO(
        Map<ServiceUseType, Boolean> serviceUseAllow, // enum 기반 동의 여부
        String name,
        String sex,
        Date birthday,
        String address,
        String[] favoriteFood
) {
    public static record UserNameUpdateDTO(
            String name
    ) {}
}