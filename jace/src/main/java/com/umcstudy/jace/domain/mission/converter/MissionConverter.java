package com.umcstudy.jace.domain.mission.converter;

import com.umcstudy.jace.domain.mission.dto.MissionResDTO;
import com.umcstudy.jace.domain.mission.entity.Mission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionItem toMissionItem(Mission mission) {
        return new MissionResDTO.MissionItem(
                mission.getId().intValue(),
                mission.getShop().getId().intValue(),
                mission.getShop().getShopName(),
                mission.getShop().getShopCategory().getShopCategoryName(),
                mission.getMissionPay(),
                mission.getMissionPoint(),
                mission.getMissionCreateTime() != null ? mission.getMissionCreateTime().toLocalDate() : null
        );
    }

    public static MissionResDTO.GetHome toGetHome(long clearMissionCnt, List<MissionResDTO.MissionItem> missionList, boolean hasNext) {
        return MissionResDTO.GetHome.builder()
                .clearMissionCnt((int) clearMissionCnt)
                .missionList(missionList)
                .hasNext(hasNext)
                .build();
    }
}
