package com.example.umc10th_week04.domain.review.controller;

import com.example.umc10th_week04.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_week04.domain.review.dto.ReviewResDTO;
import com.example.umc10th_week04.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th_week04.domain.review.service.ReviewService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, request));
    }

    @GetMapping
    public ApiResponse<ReviewResDTO.GetReview> getReview() {
        BaseSuccessCode code = ReviewSuccessCode.OK;

        return ApiResponse.onSuccess(code, reviewService.getReview());
    }
}
