package com.example.umc10th_week04.domain.mission.controller;

import com.example.umc10th_week04.domain.mission.dto.MissionReqDTO;
import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th_week04.domain.mission.service.MissionService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(userId));
    }

    //가게 내 미션 생성
    @PostMapping("/vi/stores/{storedId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storedId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storedId, dto));
    }

    // 가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionInfo>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, cursor, query));
    }

    @PatchMapping("/{missionId}/success")
    public ApiResponse<Void> successMission(
            @PathVariable Long missionId
            ) {
        BaseSuccessCode code = MissionSuccessCode.COMPLETED;
        return ApiResponse.onSuccess(code, null);
    }

}
