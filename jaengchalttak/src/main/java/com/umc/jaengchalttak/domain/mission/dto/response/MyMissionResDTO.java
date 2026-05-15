package com.umc.jaengchalttak.domain.mission.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MyMissionResDTO(
        Integer totalPoint,
        List<MyMissionDetail> myMissions
) {
    @Builder
    public record MyMissionDetail(
            Long missionId,
            String storeName,
            String missionName,
            Integer missionPoint,
            Integer missionAmount,
            LocalDateTime missionDate
    ) {}
}