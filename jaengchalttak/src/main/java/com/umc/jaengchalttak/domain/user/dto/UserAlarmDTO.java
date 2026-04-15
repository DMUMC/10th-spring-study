package com.umc.jaengchalttak.domain.user.dto;

import lombok.Builder;

public record UserAlarmDTO(
    Long userId,
    Boolean newEvent,
    Boolean review_answer,
    Boolean inquiry
) {
    @Builder
    public static record AlarmResponseDTO(
            Boolean newEvent,
            Boolean review_answer,
            Boolean inquiry
    ) {}
}

