package com.umc.jaengchalttak.domain.mission.converter;

import com.umc.jaengchalttak.domain.mission.dto.response.MissionsProgressResDTO;
import com.umc.jaengchalttak.domain.mission.dto.response.MyMissionResDTO;
import com.umc.jaengchalttak.domain.mission.entity.UserMission;
import com.umc.jaengchalttak.domain.mission.enums.CompleteStatus;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    private MissionConverter() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    public static MissionsProgressResDTO toMissionsProgressResDTO(UserMission userMission) {
        // CompleteStatus가 SUCCESS일 true로 변환
        boolean isMissionSuccess = userMission.getIsComplete() == CompleteStatus.SUCCESS;

        return MissionsProgressResDTO.builder()
                .storeId(userMission.getMission().getStore().getId())
                .storeName(userMission.getMission().getStore().getStoreName())
                .missionName(userMission.getMission().getMissionName())
                .missionPoint(userMission.getMission().getMissionPoint())
                .missionAmount(userMission.getMission().getMissionAmount())
                .isMissionSuccess(isMissionSuccess)
                .build();
    }

    public static MyMissionResDTO toMyMissionResDTO(Page<UserMission> userMissions, Integer totalPoint) {
        List<MyMissionResDTO.MyMissionDetail> missionDetails = userMissions.stream()
                .map(MissionConverter::toMyMissionDetail)
                .toList();

        return MyMissionResDTO.builder()
                .totalPoint(totalPoint)
                .myMissions(missionDetails)
                .build();
    }

    // UserMission entity를 MyMissionResDTO의 List<MyMissionDetail> 로 바꾸는 내부 메서드
    private static MyMissionResDTO.MyMissionDetail toMyMissionDetail(UserMission userMission) {
        return MyMissionResDTO.MyMissionDetail.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getStoreName())
                .missionName(userMission.getMission().getMissionName())
                .missionPoint(userMission.getMission().getMissionPoint())
                .missionAmount(userMission.getMission().getMissionAmount())
                .missionDate(userMission.getCreatedAt())
                .build();
    }

}
