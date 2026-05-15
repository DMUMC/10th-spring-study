package com.example.umc10th_week04.domain.mission.converter;

import com.example.umc10th_week04.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.entity.Mission;
import com.example.umc10th_week04.domain.mission.entity.Store;
import com.example.umc10th_week04.domain.mission.entity.UserMission;

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

    //가게 내 미션 조회
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
                .reward(mission.getReward())
                .build();
    }
}
