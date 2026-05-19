package com.example.umc10th_week04.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record Missions(
            List<MissionInfo> myMissionList
    ){}

    // 가게 내 미션 조회
    @Builder
    public record MissionInfo(
            Long missionId,
            int cost,
            int reward
    ){}

    @Builder
    public record MyMissions(
            List<MyMissionInfo> myMissionList
    ) {}

    // 유저 미션 조회
    @Builder
    public record MyMissionInfo(
            Long missionId,
            LocalDateTime period,
            Boolean completed
    ) {}

}
