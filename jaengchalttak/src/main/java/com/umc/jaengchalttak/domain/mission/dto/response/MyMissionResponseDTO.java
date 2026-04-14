package com.umc.jaengchalttak.domain.mission.dto.response;

import java.util.Date;

public record MyMissionResponseDTO(
        String storeName,
        String missionName,
        Integer missionPoint,
        Integer missionAmount,
        Date missionDate
) { }
