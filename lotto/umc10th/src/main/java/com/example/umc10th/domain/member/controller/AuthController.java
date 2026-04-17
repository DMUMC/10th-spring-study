package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/members/signup")
    public ApiResponse<MemberMissionResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }
}
