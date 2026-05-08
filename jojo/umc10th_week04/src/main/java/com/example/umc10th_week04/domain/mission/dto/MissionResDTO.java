package com.example.umc10th_week04.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MyMission(
            List<MissionInfo> myMissionList
    ){}

    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            int cost,
            int reward
    ){}
}
