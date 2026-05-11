package com.umc.jaengchalttak.domain.store.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateStoreMissionReqDTO(
        @NotBlank(message = "미션 이름은 반드시 존재해야 합니다.") // 문자열은 NotBlank
        @Size(max = 50, message = "미션 이름은 50자를 넘기면 안됩니다.")
        String missionName,

        @NotNull(message = "미션 날짜는 필수입니다.")
        @FutureOrPresent(message = "미션 날짜는 과거일 수 없습니다.")
        LocalDate missionDate,

        @NotNull(message = "포인트는 필수입니다.")
        @Min(value = 1, message = "포인트는 1점 이상이어야 합니다.")
        Integer missionPoint,

        @NotNull(message = "미션 금액은 필수입니다.")
        @Min(value = 1, message = "미션 금액은 1원 이상이어야 합니다.")
        Integer missionAmount
) { }