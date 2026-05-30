package com.example.umc10th.domain.mission.controller;



import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO.GetInfo;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.common.Pagination;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.global.common.Pagination.Pagi;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    private final MemberService memberService;


    @GetMapping("/home")
    public ApiResponse<Slice<MissionResDTO.GetInfo>> getHomeMissions(
            @RequestParam Long id,
            @RequestParam Long missionId,
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                missionService.getHomeMissions(id, missionId, locationId, size)
        );
    }

    @GetMapping("/v1/missions")
    public ApiResponse<List<MemberMissionResDTO.GetInfo>> getMemberMissions(
            @RequestParam Long id,
            @RequestParam Long missionId,
            @RequestParam List<Status> status,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                missionService.getMemberMissions(id, missionId, status, size)
        );
    }



    @GetMapping("/v2/missions")
    public ApiResponse<Pagi<MemberMissionResDTO.GetInfo>> getOffsetMemberMissions(
            @RequestParam Long id,
            @RequestParam List<Status> status,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getOffsetMemberMissions(
                id, status, size, pageNumber, sort)
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
