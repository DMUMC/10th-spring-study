package com.umc.jaengchalttak.domain.mission.dto.response;

public record MissionsProgressResponseDTO(
        String storeName,
        String missionName,
        Integer missionPoint,
        Integer missionAmount,
        Boolean isComplete
) { }
