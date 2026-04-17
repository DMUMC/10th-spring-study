package com.example.umc10th_week04.domain.review.controller;

import com.example.umc10th_week04.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_week04.domain.review.dto.ReviewResDTO;
import com.example.umc10th_week04.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th_week04.domain.review.service.ReviewService;
import com.example.umc10th_week04.domain.user.dto.UserReqDTO;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.ObjectReadContext;

@RestController
@RequestMapping("/api/mypages")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.GetReview> getReview(
            @RequestBody UserReqDTO.GetInfo dto
        ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;

        return ApiResponse.onSuccess(code, reviewService.getReview(dto));
    }
}
