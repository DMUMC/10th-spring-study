package com.example.umc10th_week04.domain.mission.controller;

import com.example.umc10th_week04.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.service.MissionService;
import com.example.umc10th_week04.domain.user.dto.UserReqDTO;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission/")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/list")
    public ApiResponse<MissionResDTO.GetMission> getMission(
            @RequestBody UserReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMission(dto));
    }

    @PatchMapping("/{missionId}/success")
    public ApiResponse<Void> successMission(
            @RequestBody MissionReqDTO.GetMissionId dto
            ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }
}
