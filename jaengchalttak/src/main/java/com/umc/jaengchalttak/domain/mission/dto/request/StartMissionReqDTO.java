package com.umc.jaengchalttak.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;

public record StartMissionReqDTO(
        @NotNull(message = "사용자 ID는 필수입니다.")
        Long userId,

        @NotNull(message = "미션 ID는 필수입니다.")
        Long missionId
) { }
