package com.umc.jaengchalttak.domain.mission.converter;

import com.umc.jaengchalttak.domain.mission.dto.response.MissionsProgressResDTO;
import com.umc.jaengchalttak.domain.mission.entity.UserMission;
import com.umc.jaengchalttak.domain.mission.enums.CompleteStatus;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;

public class MissionConverter {

    private MissionConverter() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    public static MissionsProgressResDTO toMissionsProgressResDTO(UserMission userMission) {
        // CompleteStatus가 SUCCESS일 true로 변환
        boolean isMissionSuccess = userMission.getIsComplete() == CompleteStatus.SUCCESS;

        return MissionsProgressResDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getStoreName())
                .missionName(userMission.getMission().getMissionName())
                .missionPoint(userMission.getMission().getMissionPoint())
                .missionAmount(userMission.getMission().getMissionAmount())
                .isMissionSuccess(isMissionSuccess)
                .build();
    }

}
