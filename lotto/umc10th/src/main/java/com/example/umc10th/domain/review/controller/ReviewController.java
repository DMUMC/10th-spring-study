package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ApiResponse <ReviewResDTO.GetInfo> getInfo(
            @RequestBody @Valid ReviewReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getInfo(dto));
    }

    @GetMapping("/reviews")
    public ApiResponse <ReviewResDTO.Pagination<ReviewResDTO.GetInfo>> getCursorInfo(
            @RequestBody @Valid ReviewReqDTO.GetInfo dto,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getCursorInfo(dto, pageSize, cursor, query));
    }
}
