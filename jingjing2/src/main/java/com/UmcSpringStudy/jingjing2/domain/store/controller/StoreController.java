package com.UmcSpringStudy.jingjing2.domain.store.controller;

import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.review.dto.response.ReviewPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.StoreCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.StoreUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.StoreDetailResponse;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.StorePreviewResponse;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    //가게 목록 조회 (카테고리 필터링 포함)
    @GetMapping("")
    public CommonResponse<List<StorePreviewResponse>> getStoreList(
            @RequestParam(value = "category", required = false) String category) {
        return CommonResponse.success("가게 목록 조회 성공", null);
    }

    //가게 상세 정보 조회
    @GetMapping("/{storeId}")
    public CommonResponse<StoreDetailResponse> getStore(@PathVariable Long storeId) {
        return CommonResponse.success("가게 상세 정보 조회 성공", null);
    }

    //특정 가게의 리뷰 목록 조회
    //Review 도메인의 Preview DTO를 활용.
    @GetMapping("/{storeId}/reviews")
    public CommonResponse<List<ReviewPreviewResponse>> getStoreReviews(@PathVariable Long storeId) {
        return CommonResponse.success("가게 리뷰 목록 조회 성공", null);
    }

    //특정 가게의 미션 목록 조회
    //Mission 도메인의 Preview DTO를 활용.
    @GetMapping("/{storeId}/missions")
    public CommonResponse<List<MissionPreviewResponse>> getStoreMissions(@PathVariable Long storeId) {
        return CommonResponse.success("가게 미션 목록 조회 성공", null);
    }

    //가게 등록
    @PostMapping("")
    public CommonResponse<StoreDetailResponse> createStore(@RequestBody StoreCreateRequest request) {
        return CommonResponse.success("가게 등록 성공", null);
    }

    //가게 수정
    @PatchMapping("/{storeId}")
    public CommonResponse<StoreDetailResponse> updateStore(
            @PathVariable Long storeId,
            @RequestBody StoreUpdateRequest request) {
        return CommonResponse.success("가게 정보 수정 성공", null);
    }
    //가게 삭제
    @DeleteMapping("/{storeId}")
    public CommonResponse<Void> deleteStore(@PathVariable Long storeId) {
        return CommonResponse.success("가게 삭제 성공", null);
    }
}