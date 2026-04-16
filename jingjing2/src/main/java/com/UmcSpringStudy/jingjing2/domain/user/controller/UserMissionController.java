package com.UmcSpringStudy.jingjing2.domain.user.controller;

import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.request.UserMissionUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionResponse;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/missions")
public class UserMissionController {

    // 특정 유저 진행 중인 미션 출력
    @GetMapping
    public CommonResponse<UserMissionPreviewResponse> getMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page) {
        return CommonResponse.success("진행 중인 미션 목록 조회 성공", null);
    }

    // 특정 유저 미션 도전 시작
    @PostMapping
    public CommonResponse<UserMissionResponse> addMission(
            @PathVariable Long userId,
            @RequestBody Long missionId) {
        return CommonResponse.success("미션 도전 시작 성공", null);
    }

    // 특정 유저 진행 중인 미션 수정
    @PatchMapping("/{missionId}")
    public CommonResponse<UserMissionResponse> updateMission(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @RequestBody UserMissionUpdateRequest request) {
        return CommonResponse.success("미션 상태 수정 성공", null);
    }

    // 특정 유저 미션 포기/삭제
    @DeleteMapping("/{missionId}")
    public CommonResponse<Void> deleteMission(
            @PathVariable Long userId,
            @PathVariable Long missionId) {
        return CommonResponse.success("미션 삭제 성공", null);
    }
}