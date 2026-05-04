package com.umc.jaengchalttak.domain.user.dto;

import com.umc.jaengchalttak.domain.user.enums.Address;
import com.umc.jaengchalttak.domain.user.enums.Gender;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseTitle;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record UserInfoDTO(
        Map<ServiceUseTitle, Boolean> serviceUseAllow, // enum 기반 동의 여부
        String name,
        Gender gender,
        LocalDate birthday,
        String address,
        Integer phoneNumber,
        Integer point
) {
    @Builder
    public static record userNameUpdateDTO(
            String name
    ) {}
}