package com.example.umc10th_week04.domain.mission.converter;

import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.entity.Mission;
import com.example.umc10th_week04.domain.mission.entity.UserMission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MyMission toGetMission(List<UserMission> userMissions) {
        return MissionResDTO.MyMission.builder()
                .myMissionList(userMissions.stream()
                        .map(MissionConverter::toMissionInfo)
                        .toList())
                .build();
    }

    public static MissionResDTO.MissionInfo toMissionInfo(UserMission userMission) {
        Mission mission = userMission.getMission();

        return MissionResDTO.MissionInfo.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .cost(mission.getCost())
                .reward(mission.getPoint())
                .build();
    }
}
