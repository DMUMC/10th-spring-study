package com.example.umc10th_week04.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetMission(
            List<MissionInfo> progresses,
            List<MissionInfo> completions
    ){}

    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            String content,
            Double reward
    ){}
}
