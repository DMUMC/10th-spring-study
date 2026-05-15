package com.example.umc10th_week04.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MyMission(
            List<MissionInfo> myMissionList
    ){}

    // 가게 내 미션 조회
    @Builder
    public record MissionInfo(
            Long missionId,
            String storeName,
            int cost,
            int reward
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
