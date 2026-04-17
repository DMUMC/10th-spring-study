package com.umc.jaengchalttak.domain.user.dto;

import com.umc.jaengchalttak.domain.user.enums.Gender;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseType;
import lombok.Builder;

import java.util.Date;
import java.util.Map;

@Builder
public record UserInfoDTO(
        Map<ServiceUseType, Boolean> serviceUseAllow, // enum 기반 동의 여부
        String name,
        Gender gender,
        Date birthday,
        String address,
        String[] favoriteFood
) {
    @Builder
    public static record UserNameUpdateDTO(
            String name
    ) {}
}