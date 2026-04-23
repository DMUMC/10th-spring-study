package com.umcstudy.jace.domain.review.controller;

import com.umcstudy.jace.domain.mission.dto.MissionReqDTO;
import com.umcstudy.jace.domain.mission.exception.code.MissionSuccessCode;
import com.umcstudy.jace.domain.review.dto.ReviewReqDTO;
import com.umcstudy.jace.domain.review.dto.ReviewResDTO;
import com.umcstudy.jace.domain.review.exception.code.ReviewSuccessCode;
import com.umcstudy.jace.domain.review.service.ReviewService;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("shops/{shopId}/reviews")
    public ApiResponse<ReviewResDTO.PostReviewWrite> postReviewWrite(
            @PathVariable Long shopId,
            @RequestBody ReviewReqDTO.PostReviewWrite dto
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.postReviewWrite(dto, shopId));
    }
}
