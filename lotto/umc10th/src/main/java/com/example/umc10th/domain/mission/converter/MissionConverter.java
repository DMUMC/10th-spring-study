package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {

    public static MissionResDTO.GetInfo toGetInfo(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return MissionResDTO.GetInfo.builder()
                .missionId(mission)
                .title(mission.getTitle())
                .point(mission.getPoint())
                .storeName(mission.getStore().getName())
                .information(memberMission.getMission().getStore().getInformation())
                .deadline(memberMission.getDeadline())
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberMissionResDTO.GetInfo toGetHomeInfo(Mission mission) {
        return MemberMissionResDTO.GetInfo.builder()
                .missionId(mission)
                .title(mission.getTitle())
                .point(mission.getPoint())
                .storeName(mission.getStore().getName())
                .information(mission.getStore().getInformation())
                .build();
    }
}