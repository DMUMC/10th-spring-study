package com.umcstudy.jace.domain.mission.converter;

import com.umcstudy.jace.domain.mission.dto.MissionResDTO;
import com.umcstudy.jace.domain.mission.entity.Mission;
import com.umcstudy.jace.domain.mission.entity.mapping.MissionUser;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionItem toMissionItem(Mission mission) {
        return MissionResDTO.MissionItem.builder()
                .missionId(mission.getId().intValue())
                .shopId(mission.getShop().getId().intValue())
                .shopName(mission.getShop().getShopName())
                .shopCategory(mission.getShop().getShopCategory().getShopCategoryName())
                .missionPay(mission.getMissionPay())
                .missionPoint(mission.getMissionPoint())
                .createDate(mission.getMissionCreateTime() != null ? mission.getMissionCreateTime().toLocalDate() : null)
                .build();
    }

    public static MissionResDTO.GetHome toGetHome(long clearMissionCnt, List<MissionResDTO.MissionItem> missionList, boolean hasNext) {
        return MissionResDTO.GetHome.builder()
                .clearMissionCnt((int) clearMissionCnt)
                .missionList(missionList)
                .hasNext(hasNext)
                .build();
    }

    public static MissionResDTO.MyMissionItem toMyMissionItem(MissionUser missionUser) {
        Mission mission = missionUser.getMission();
        return MissionResDTO.MyMissionItem.builder()
                .userMissionId(missionUser.getId())
                .missionId(mission.getId().intValue())
                .shopId(mission.getShop().getId().intValue())
                .shopName(mission.getShop().getShopName())
                .shopCategory(mission.getShop().getShopCategory().getShopCategoryName())
                .missionPay(mission.getMissionPay())
                .missionPoint(mission.getMissionPoint())
                .missionCondition(missionUser.getMissionCondition())
                .build();
    }

    public static MissionResDTO.GetMyMission toGetMyMission(List<MissionResDTO.MyMissionItem> missionList, boolean hasNext) {
        return MissionResDTO.GetMyMission.builder()
                .missionList(missionList)
                .hasNext(hasNext)
                .build();
    }
}
