package com.umc.jaengchalttak.domain.user.dto;

public record UserAlarmDTO(
    Long userId,
    Boolean newEvent,
    Boolean review_answer,
    Boolean inquiry
) {
    public static record AlarmResponseDTO(
            Boolean newEvent,
            Boolean review_answer,
            Boolean inquiry
    ) {}
}

