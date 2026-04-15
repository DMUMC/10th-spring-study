package com.umc.jaengchalttak.domain.mission.dto.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record MyMissionResDTO(
        String storeName,
        String missionName,
        Integer missionPoint,
        Integer missionAmount,
        Date missionDate
) { }
