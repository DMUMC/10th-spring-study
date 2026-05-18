package com.umc.jaengchalttak.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record UserAlarmDTO(
    @NotNull(message = "사용자 ID는 필수입니다.")
    Long userId,

    @NotNull(message = "새로운 이벤트 알림 설정은 필수입니다.")
    Boolean newEvent,

    @NotNull(message = "리뷰 답글 알림 설정은 필수입니다.")
    Boolean reviewAnswer,

    @NotNull(message = "문의 알림 설정은 필수입니다.")
    Boolean inquiry
) {
    @Builder
    public static record alarmResDTO(
            Boolean newEvent,
            Boolean review_answer,
            Boolean inquiry
    ) {}
}

