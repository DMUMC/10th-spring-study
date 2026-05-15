package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {
    public record GetInfo(
            @NotNull(message = "멤버 아이디 입력은 필수입니다.")
            Long id
    ) {}
}