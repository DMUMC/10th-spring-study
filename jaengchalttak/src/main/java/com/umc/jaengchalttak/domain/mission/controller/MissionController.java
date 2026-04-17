package com.umc.jaengchalttak.domain.mission.controller;

import com.umc.jaengchalttak.domain.mission.dto.request.CompleteMissionReqDTO;
import com.umc.jaengchalttak.domain.mission.dto.request.StartMissionReqDTO;
import com.umc.jaengchalttak.domain.mission.dto.response.MissionsProgressResDTO;
import com.umc.jaengchalttak.domain.mission.dto.response.MyMissionResDTO;
import com.umc.jaengchalttak.domain.mission.payload.code.MissionSuccessCode;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/mission")
public class MissionController {

    // ====== 현재 가능한 내 미션 조회 ======
    @GetMapping
    public ApiResponse<List<MyMissionResDTO>> getMyMissionList(@RequestParam("userId") Long userId,
                                                               @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<MyMissionResDTO> result = List.of(
                MyMissionResDTO.builder()
                        .storeName("스타벅스 강남점")
                        .missionName("아메리카노 2잔 구매")
                        .missionPoint(500)
                        .missionAmount(2)
                        .missionDate(new Date())
                        .build()
        );

        BaseSuccessCode code = MissionSuccessCode.MY_MISSION_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 진행 중/진행 완료 미션 조회 ======
    @GetMapping("/me")
    public ApiResponse<List<MissionsProgressResDTO>> getMissionsByProgress(@RequestParam("userId") Long userId,
                                                                           @RequestParam("isProgress") boolean isProgress,
                                                                           @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<MissionsProgressResDTO> result = List.of(
                MissionsProgressResDTO.builder()
                        .storeName("스타벅스 강남점")
                        .missionName("아메리카노 2잔 구매")
                        .missionPoint(500)
                        .missionAmount(2)
                        .isComplete(true)
                        .build()
        );

        BaseSuccessCode code = MissionSuccessCode.MISSION_BY_PROGRESS_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 미션 수행 ======
    @PostMapping
    public ApiResponse<String> startMission(@RequestBody StartMissionReqDTO request) {
        BaseSuccessCode code = MissionSuccessCode.START_MISSION_CREATED;
        return ApiResponse.onSuccess(code, "미션 진행 시작!");
    }


    // ====== 미션 성공 요청 ======
    @PostMapping("/success")
    public ApiResponse<String> completeMission(@RequestBody CompleteMissionReqDTO request) {
        BaseSuccessCode code = MissionSuccessCode.COMPLETE_MISSION_CREATED;
        return ApiResponse.onSuccess(code, "미션 수행 완료!");
    }
}
