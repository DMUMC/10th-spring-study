package com.umc.jaengchalttak.domain.user.dto;

import com.umc.jaengchalttak.domain.user.enums.Address;
import com.umc.jaengchalttak.domain.user.enums.Gender;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseTitle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record UserInfoDTO(
        Map<ServiceUseTitle, Boolean> serviceUseAllow, // enum 기반 동의 여부

        @NotBlank(message = "이름은 필수입니다.")
        @Size(max = 45, message = "이름은 45자 이내여야 합니다.")
        String name,

        Gender gender,

        @NotNull(message = "생년월일은 필수입니다.")
        @Past(message = "생년월일은 과거 날짜여야 합니다.")
        LocalDate birthday,

        @NotBlank(message = "주소는 필수입니다.")
        Address address,

        Integer phoneNumber,

        Integer point
) {
    @Builder
    public static record userNameUpdateDTO(
            @NotBlank(message = "변경할 이름은 필수입니다.")
            @Size(max = 45, message = "이름은 45자 이내여야 합니다.")
            String name
    ) {}
}