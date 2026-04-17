package com.umc.jaengchalttak.domain.mission.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MyMissionResDTO(
        String storeName,
        String missionName,
        Integer missionPoint,
        Integer missionAmount,
        LocalDateTime missionDate
) { }
