package com.umcstudy.jace.domain.mission.controller;

import com.umcstudy.jace.domain.mission.controller.docs.MissionControllerDocs;
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
public class MissionController implements MissionControllerDocs {

    private final MissionService missionService;

    @Override
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.GetHome> getHome(
            @RequestParam String region,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.HomeOK;
        return ApiResponse.onSuccess(code, missionService.getHome(region, cursorId, size));
    }

    @Override
    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResDTO.GetMyMission> getMyMission(
            @RequestParam MissionStatus missionCondition,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MyMissionOK;
        return ApiResponse.onSuccess(code, missionService.getMyMission(missionCondition, page, size));
    }

    @Override
    @PatchMapping("/users/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.PatchMissionSuc> patchMissionSuc(
            @PathVariable Integer missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.MyMissionOK;
        return ApiResponse.onSuccess(code, missionService.patchMissionSuc(missionId));
    }
}
