package com.UmcSpringStudy.jingjing2.domain.mission.controller;

import com.UmcSpringStudy.jingjing2.domain.mission.dto.request.MissionCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.request.MissionUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionDetailResponse;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.mission.service.MissionService;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionService missionService;
    //전체 미션 목록 조회
    @GetMapping("/all")
    public CommonResponse<List<MissionPreviewResponse>> getMissionList() {

        return CommonResponse.success("미션 목록 조회 성공", null);
    }

    //특정 미션 상세 조회
    @GetMapping("/{missionId}")
    public CommonResponse<MissionDetailResponse> getMission(@PathVariable Long missionId) {

        return CommonResponse.success("미션 상세 조회 성공", null);
    }

    //신규 미션 생성
    @PostMapping("/new")
    public CommonResponse<MissionDetailResponse> createMission(
            @RequestBody @Valid MissionCreateRequest request) {
        return CommonResponse.success("미션 생성 성공", missionService.createMission(request));
    }

    //미션 수정 (부분 수정)
    @PatchMapping("/update/{missionId}")
    public CommonResponse<MissionDetailResponse> updateMission(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionUpdateRequest request) {

        return CommonResponse.success("미션 수정 성공", null);
    }

     //미션 삭제
    @DeleteMapping("/delete/{missionId}")
    public CommonResponse<Void> deleteMission(@PathVariable Long missionId) {

        return CommonResponse.success("미션 삭제 성공", null);
    }

}