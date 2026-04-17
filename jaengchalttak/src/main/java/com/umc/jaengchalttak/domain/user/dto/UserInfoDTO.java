package com.umc.jaengchalttak.domain.user.dto;

import com.umc.jaengchalttak.domain.user.enums.Gender;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
public record UserInfoDTO(
        Map<ServiceUseType, Boolean> serviceUseAllow, // enum 기반 동의 여부
        String name,
        Gender gender,
        LocalDateTime birthday,
        String address,
        List<String> favoriteFood
) {
    @Builder
    public static record userNameUpdateDTO(
            String name
    ) {}
}