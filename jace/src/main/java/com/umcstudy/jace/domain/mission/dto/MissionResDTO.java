package com.umcstudy.jace.domain.mission.dto;

import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    public record MissionItem(
            Integer missionId,
            Integer shopId,
            String shopName,
            String shopCategory,
            Integer missionPay,
            Integer missionPoint,
            LocalDate createDate
    ){}

    @Builder
    public record GetHome(
            Integer clearMissionCnt,
            List<MissionItem> missionList
    ){}

    public record MyMissionItem(
            Integer missionId,
            Integer shopId,
            String shopName,
            String shopCategory,
            Integer missionPay,
            Integer missionPoint,
            MissionStatus missionCondition
    ){}

    @Builder
    public record GetMyMission(
            List<MyMissionItem> missionList
    ){}

    @Builder
    public record PatchMissionSuc(
            Integer mission_suc_code
    ){}
}
