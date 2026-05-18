package com.example.umc10th_week04.domain.mission.converter;

import com.example.umc10th_week04.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.entity.Mission;
import com.example.umc10th_week04.domain.mission.entity.Store;
import com.example.umc10th_week04.domain.mission.entity.UserMission;
import com.example.umc10th_week04.domain.user.entity.User;

import java.util.List;

public class MissionConverter {

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagenation (
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.MissionInfo toGetMission(
            Mission mission
    ) {
        return MissionResDTO.MissionInfo.builder()
                .reward(mission.getReward())
                .cost(mission.getCost())
                .missionId(mission.getId())
                .build();
    }

    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .reward(dto.reward())
                .cost(dto.cost())
                .build();
    }

    // 유저 미션 조회
    public static <T> MissionResDTO.OffsetPagination<T> toOffsetPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MissionResDTO.OffsetPagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    public static MissionResDTO.MyMissionInfo toGetMyMission(
            UserMission userMission
    ) {
        return MissionResDTO.MyMissionInfo.builder()
                .period(userMission.getPeriod())
                .completed(userMission.getCompleted())
                .missionId(userMission.getMission().getId())
                .build();
    }
}
