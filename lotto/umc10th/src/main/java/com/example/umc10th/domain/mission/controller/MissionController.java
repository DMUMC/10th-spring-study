package com.example.umc10th.domain.mission.controller;



import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO.GetInfo;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO.Pagination;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
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
    public ApiResponse<List<MissionResDTO.GetInfo>> getHomeMissions(
            @RequestBody @Valid MissionReqDTO.GetInfo dto,
            @RequestParam Long missionId,
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                missionService.getHomeMissions(dto, missionId, locationId, size)
        );
    }

    @GetMapping("/v1/missions")
    public ApiResponse<List<MemberMissionResDTO.GetInfo>> getMemberMissions(
            @RequestBody @Valid MissionReqDTO.GetInfo dto,
            @RequestParam Long missionId,
            @RequestParam List<Status> status,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                missionService.getMemberMissions(dto, missionId, status, size)
        );
    }



    @GetMapping("/v2/missions")
    public ApiResponse<Pagination<MemberMissionResDTO.GetInfo>> getOffsetMemberMissions(
            @RequestBody @Valid MissionReqDTO.GetInfo dto,
            @RequestParam List<Status> status,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getOffsetMemberMissions(
                dto, status, size, pageNumber, sort)
        );
    }

   // @PutMapping("/missions/{missionId}")
  //  public ApiResponse <MissionResDTO.GetInfo> getInfo(
  //          @PathVariable Long missionId
  //  ){
  //      BaseSuccessCode code = MissionSuccessCode.OK;
   //     return ApiResponse.onSuccess(code, missionService.getInfo(missionId));
  //  }
}
