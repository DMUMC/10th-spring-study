package com.umc.jaengchalttak.domain.mission.dto.response;

import lombok.Builder;

@Builder
public record MissionsProgressResDTO(
        Long storeId,
        String storeName,
        String missionName,
        Integer missionPoint,
        Integer missionAmount,
        Boolean isMissionSuccess
) { }
