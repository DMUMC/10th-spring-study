package com.example.umc10th_week04.domain.mission.controller;

import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th_week04.domain.mission.service.MissionService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 아직 토큰을 다루는 기능을 익히지 못하여서 임의로 request URL에 User ID 를 받습니다.
    @GetMapping("/list/{userId}")
    public ApiResponse<MissionResDTO.MyMission> getMyMission(
            @PathVariable Long userId
    ) {
        BaseSuccessCode code = MissionSuccessCode.READ_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(userId));
    }

    @PatchMapping("/{missionId}/success")
    public ApiResponse<Void> successMission(
            @PathVariable Long missionId
            ) {
        BaseSuccessCode code = MissionSuccessCode.READ_SUCCESS;
        return ApiResponse.onSuccess(code, null);
    }
}
