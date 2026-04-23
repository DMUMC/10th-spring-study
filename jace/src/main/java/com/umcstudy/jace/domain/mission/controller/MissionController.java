package com.umcstudy.jace.domain.mission.controller;

import com.umcstudy.jace.domain.mission.dto.MissionReqDTO;
import com.umcstudy.jace.domain.mission.dto.MissionResDTO;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import com.umcstudy.jace.domain.mission.exception.code.MissionSuccessCode;
import com.umcstudy.jace.domain.mission.service.MissionService;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/home")
    public ApiResponse<MissionResDTO.GetHome> getHome(
            @RequestParam MissionReqDTO.GetHome dto
    ){
        BaseSuccessCode code = MissionSuccessCode.HomeOK;
        return ApiResponse.onSuccess(code, missionService.getHome(dto));
    }

    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResDTO.GetMyMission> getMyMission(
            @RequestParam MissionStatus missionCondition
    ){
        BaseSuccessCode code = MissionSuccessCode.MyMissionOK;
        return ApiResponse.onSuccess(code, missionService.getMyMission(missionCondition));
    }

    @PatchMapping("/users/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.PatchMissionSuc> patchMissionSuc(
            @PathVariable Integer missionId
    ){
        BaseSuccessCode code = MissionSuccessCode.MyMissionOK;
        return ApiResponse.onSuccess(code, missionService.patchMissionSuc(missionId));
    }
}
