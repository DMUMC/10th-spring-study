package com.umc.jaengchalttak.domain.mission.controller;

import com.umc.jaengchalttak.domain.mission.dto.request.CompleteMissionReqDTO;
import com.umc.jaengchalttak.domain.mission.dto.request.StartMissionReqDTO;
import com.umc.jaengchalttak.domain.mission.dto.response.MissionsProgressResDTO;
import com.umc.jaengchalttak.domain.mission.dto.response.MyMissionResDTO;
import com.umc.jaengchalttak.domain.mission.payload.code.MissionSuccessCode;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/mission")
public class MissionController {

    @Operation(summary = "현재 가능한 내 미션 목록 조회", description = "유저가 현재 도전할 수 있는 새로운 미션 목록을 조회합니다.")
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
                        .missionDate(LocalDateTime.now())
                        .build()
        );

        BaseSuccessCode code = MissionSuccessCode.MY_MISSION_OK;
        return ApiResponse.onSuccess(code, result);
    }


    @Operation(summary = "진행 상태별 내 미션 조회", description = "유저가 진행 중이거나 이미 완료한 미션 목록을 필터링하여 조회합니다.")
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


    @Operation(summary = "미션 도전하기", description = "특정 미션을 수행 목록에 추가하고 진행 상태로 변경합니다.")
    @PostMapping
    public ApiResponse<String> startMission(@RequestBody StartMissionReqDTO request) {
        BaseSuccessCode code = MissionSuccessCode.START_MISSION_CREATED;
        return ApiResponse.onSuccess(code, "미션 진행 시작!");
    }


    @Operation(summary = "미션 완료 인증", description = "진행 중인 미션에 대해 완료 증빙을 제출하고 성공 처리를 요청합니다.")
    @PostMapping("/success")
    public ApiResponse<String> completeMission(@RequestBody CompleteMissionReqDTO request) {
        BaseSuccessCode code = MissionSuccessCode.COMPLETE_MISSION_CREATED;
        return ApiResponse.onSuccess(code, "미션 수행 완료!");
    }
}
