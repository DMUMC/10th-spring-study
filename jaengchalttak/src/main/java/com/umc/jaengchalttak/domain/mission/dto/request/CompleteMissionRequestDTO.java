package com.umc.jaengchalttak.domain.mission.dto.request;

public record CompleteMissionRequestDTO(
        Long userId,
        Long missionId,
        Long storeId
) { }
