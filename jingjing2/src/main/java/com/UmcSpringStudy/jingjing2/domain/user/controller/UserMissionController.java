package com.UmcSpringStudy.jingjing2.domain.user.controller;

import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.request.UserMissionUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionResponse;
import com.UmcSpringStudy.jingjing2.domain.user.service.UserMissionService;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated // PathVariable이나 단순 파라미터 검증을 위해 추가
@RequestMapping("/api/users/{userId}/missions")
public class UserMissionController {
    private final UserMissionService userMissionService;

    // 특정 유저 진행 중인 미션 출력
    @GetMapping
    public CommonResponse<UserMissionPreviewResponse> getMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "All") String option) {
        return CommonResponse.success("미션 목록 조회 성공", userMissionService.getUserMissions(userId, page, option));
    }

    // 미션 추가
    @PostMapping
    public CommonResponse<UserMissionResponse> addMission(
            @PathVariable Long userId,
            @RequestBody Long missionId) {
        return CommonResponse.success("미션 도전 시작 성공", userMissionService.addMission(userId, missionId));
    }

    //미션 수정
    @PatchMapping("/{missionId}")
    public CommonResponse<UserMissionResponse> updateMission(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @RequestBody @Valid UserMissionUpdateRequest request) {
        return CommonResponse.success("미션 상태 수정 성공", userMissionService.updateMission(userId, missionId, request));
    }

    // 특정 유저 미션 포기/삭제
    @DeleteMapping("/{missionId}")
    public CommonResponse<Void> deleteMission(
            @PathVariable Long userId,
            @PathVariable Long missionId) {
        return CommonResponse.success("미션 삭제 성공", null);
    }

    //특정 구역에서 새로운 미션 탐색
    @GetMapping("/available-regions/{regionId}")
    public CommonResponse<List<MissionPreviewResponse>> getAvailableMissions(
            @PathVariable Long userId,
            @PathVariable Long regionId) {
        List<MissionPreviewResponse> result = userMissionService.getAvailableMissionsByRegion(userId, regionId);
        return CommonResponse.success("도전 가능한 구역별 미션 목록 조회 성공", result);
    }
}