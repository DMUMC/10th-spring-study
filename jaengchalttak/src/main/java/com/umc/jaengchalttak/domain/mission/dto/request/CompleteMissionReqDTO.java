package com.umc.jaengchalttak.domain.mission.dto.request;

public record CompleteMissionReqDTO(
        Long userId,
        Long missionId,
        Long storeId
) { }
