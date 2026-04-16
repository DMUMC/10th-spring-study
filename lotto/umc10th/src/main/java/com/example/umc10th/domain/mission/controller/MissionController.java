package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    private final MemberService memberService;


    @GetMapping("/home")
    public ApiResponse <MemberMissionResDTO.GetInfo> getInfo(
            @RequestParam String storeName
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getInfo(storeName));
    }

    @GetMapping("/missions")
    public ApiResponse <MissionResDTO.GetInfo> getInfo(
            @RequestParam List<Status> status
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getInfo(status));
    }

    @PutMapping("/missions/{missionId}")
    public ApiResponse <MissionResDTO.GetInfo> getInfo(
            @PathVariable Long missionId
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getInfo(missionId));
    }
}
