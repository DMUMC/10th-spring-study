package com.example.umc10th_week04.domain.view.home.converter;

import com.example.umc10th_week04.domain.mission.entity.Mission;
import com.example.umc10th_week04.domain.mission.entity.UserMission;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.view.home.dto.HomeResDTO;

import java.util.List;

public class HomeConverter {

    public static HomeResDTO.GetInfo toGetInfo(
            User user,
            String currentLocation,
            List<Mission> availableMissions,
            List<UserMission> completedMissions
    ) {
        return HomeResDTO.GetInfo.builder()
                .currentLocation(currentLocation)
                .point(user.getPoint())
                .availableMissions(availableMissions.stream()
                        .map(HomeConverter::toMissionInfo)
                        .toList())
                .completedMissions(completedMissions.stream()
                        .map(HomeConverter::toMissionInfo)
                        .toList())
                .build();
    }

    public static HomeResDTO.MissionInfo toMissionInfo(Mission mission) {
        return HomeResDTO.MissionInfo.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getCost())
                .rewardPoint(mission.getReward())
                .build();
    }

    public static HomeResDTO.MissionInfo toMissionInfo(UserMission userMission) {
        return toMissionInfo(userMission.getMission());
    }
}
