package com.example.umc10th_week04.domain.view.home.dto;

import lombok.Builder;

import java.util.List;

public class HomeResDTO {

    @Builder
    public record GetInfo(
            Integer point,
            MissionCurrent missionCurrent,
            List<MissionInfo> missions
    ){}

    @Builder
    public record MissionCurrent(
            Integer current,
            Integer total,
            Integer reward
    ){}

    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            String category,
            Integer missionContent,
            Integer rewardPoint,
            String duration
    ){}

}
