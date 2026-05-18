package com.umc.jaengchalttak.domain.store.converter;

import com.umc.jaengchalttak.domain.mission.entity.Mission;
import com.umc.jaengchalttak.domain.store.dto.request.CreateStoreMissionReqDTO;
import com.umc.jaengchalttak.domain.store.dto.response.GetStoreMissionResDTO;
import com.umc.jaengchalttak.domain.store.entity.Store;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;

public class StoreMissionConverter {

    // 객체 생성하면 예외
    private StoreMissionConverter() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    public static Mission toMission(Store store, CreateStoreMissionReqDTO request) {
        return Mission.builder()
                .missionName(request.missionName())
                .missionPoint(request.missionPoint())
                .missionAmount(request.missionAmount())
                .missionDate(request.missionDate())
                .store(store)
                .build();
    }

    public static GetStoreMissionResDTO toGetStoreMissionResDTO(Mission mission) {
        return GetStoreMissionResDTO.builder()
                .missionId(mission.getId())
                .missionAmount(mission.getMissionAmount())
                .missionPoint(mission.getMissionPoint())
                .build();
    }

}
