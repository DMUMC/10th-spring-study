package com.umc.jaengchalttak.domain.store.controller;

import com.umc.jaengchalttak.domain.store.dto.request.CommentReqDTO;
import com.umc.jaengchalttak.domain.store.dto.request.StoreReviewReqDTO;
import com.umc.jaengchalttak.domain.store.dto.response.StoreReviewListResDTO;
import com.umc.jaengchalttak.domain.store.enums.QueryType;
import com.umc.jaengchalttak.domain.store.payload.code.StoreSuccessCode;
import com.umc.jaengchalttak.domain.store.service.ReviewService;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.global.dto.CursorPagination;
import com.umc.jaengchalttak.global.security.entity.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store/review")
@Tag(name = "가게 리뷰 API", description = "가게 리뷰 및 사장님 답글 관련 API입니다.")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "가게 리뷰 목록 조회", description = "특정 가게에 작성된 리뷰와 사장님 답글 목록을 페이징하여 조회합니다.")
    @GetMapping
    public ApiResponse<List<StoreReviewListResDTO>> getStoreReviewList(@RequestParam("storeId") Long storeId,
                                                                       @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<StoreReviewListResDTO> result = List.of(
                StoreReviewListResDTO.builder()
                        .userId(1L)
                        .userName("홍길동")
                        .reviewId(101L)
                        .reviewStar(5.0)
                        .reviewContent("커피가 정말 맛있어요!")
                        .reviewCreatedAt(LocalDateTime.now())
                        .commentId(101L)
                        .commentContent("감사합니다 😊")
                        .commentCreateAt(LocalDateTime.now())
                        .build()
        );

        BaseSuccessCode code = StoreSuccessCode.REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }


    @Operation(
            summary = "내 리뷰 목록 보기",
            description = "특정 가게에 작성된 리뷰와 사장님 답글 목록 중 내 리뷰를 페이징하여 조회합니다.")
    @GetMapping("/me")
    public ApiResponse<CursorPagination<StoreReviewListResDTO>> getMyStoreReview(
            @AuthenticationPrincipal AuthUser user,
            @RequestParam Long storeId,

            @RequestParam(defaultValue = "3")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer pageSize,

            @RequestParam(required = false)
            String cursor,

            @RequestParam
            QueryType query
    ) {
        CursorPagination<StoreReviewListResDTO> result =
                reviewService.getMyReviewList(user.getUser().getId(), storeId, pageSize, cursor, query);

        BaseSuccessCode code = StoreSuccessCode.MY_REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }



    @Operation(summary = "가게 리뷰 작성", description = "유저가 방문한 가게에 대해 별점과 사진을 포함한 리뷰를 작성합니다.")
    @PostMapping
    public ApiResponse<String> writerReview(@Valid @RequestBody StoreReviewReqDTO request,
                                            @AuthenticationPrincipal AuthUser user) {
        reviewService.createReview(request, user.getUser().getId()); // 리뷰 생성

        BaseSuccessCode code = StoreSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code, "리뷰 작성 완료!");
    }


    @Operation(summary = "사장님 댓글 작성", description = "가게 주인이 유저의 리뷰에 대해 답글(댓글)을 작성합니다.")
    @PostMapping("/comment")
    public ApiResponse<String> writerComment(@Valid @RequestBody CommentReqDTO request) {
        BaseSuccessCode code = StoreSuccessCode.COMMENT_CREATED;
        return ApiResponse.onSuccess(code, "댓글 작성 완료!");
    }

}
