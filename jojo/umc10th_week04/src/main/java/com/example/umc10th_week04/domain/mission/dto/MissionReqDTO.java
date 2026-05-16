package com.example.umc10th_week04.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionReqDTO {

    @Builder
    public record CreateMission(
            @NotNull(message = "마감 기한은 필수 입니다.")
            LocalDateTime period,
            @NotNull(message = "미션 성공 포인트는 필수 입니다.")
            Integer reward,
            @NotNull(message = "조건은 빈칸일 수 없습니다.")
            Integer cost
    ){}

    @Builder
    public record UserMission(
            @NotNull(message = "유저 ID는 필수 입니다.")
            Long userId
    ){}
}
