package com.example.umc10th_week04.domain.view.home.dto;

import lombok.Builder;

import java.util.List;

public class HomeResDTO {

    @Builder
    public record GetInfo(
            String currentLocation,
            Integer point,
            List<MissionInfo> availableMissions,
            List<MissionInfo> completedMissions
    ){}

    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            Integer missionContent,
            Integer rewardPoint
    ){}

}
