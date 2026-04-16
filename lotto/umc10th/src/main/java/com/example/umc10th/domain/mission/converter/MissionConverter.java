package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {

    public static MemberMissionResDTO.GetInfo toGetInfo(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.GetInfo.builder()
                .missionId(memberMission.getMission())
                .title(memberMission.getMission().getTitle())
                .point(memberMission.getMission().getPoint())
                .storeName(memberMission.getMission().getStore().getName())
                .information(memberMission.getMission().getStore().getInformation())
                .deadline(memberMission.getDeadline())
                .build();
    }
}
