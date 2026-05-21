package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.common.Pagination;

import java.util.List;

public class MissionConverter {

    public static MemberMissionResDTO.GetInfo toGetInfo(MemberMission memberMission) {

        Mission mission = memberMission.getMission();

        return MemberMissionResDTO.GetInfo.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .point(mission.getPoint())
                .storeName(mission.getStore().getName())
                .information(mission.getStore().getInformation())
                .deadline(memberMission.getDeadline())
                .status(memberMission.getStatus())
                .build();
    }

    // Mission -> MissionResDTO
    public static MissionResDTO.GetInfo toGetHomeInfo(Mission mission) {

        return MissionResDTO.GetInfo.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .point(mission.getPoint())
                .storeName(mission.getStore().getName())
                .information(mission.getStore().getInformation())
                .build();
    }



    public static <T> Pagination.Pagi<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return Pagination.Pagi.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}