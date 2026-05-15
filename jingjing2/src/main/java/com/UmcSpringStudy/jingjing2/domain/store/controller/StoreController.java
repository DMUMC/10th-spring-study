package com.UmcSpringStudy.jingjing2.domain.store.controller;

import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.StoreCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.StoreUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.RegionRequestDTO;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.StoreResponse;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.RegionResponseDTO;
import com.UmcSpringStudy.jingjing2.domain.store.service.RegionService;
import com.UmcSpringStudy.jingjing2.domain.store.service.StoreService;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final RegionService regionService;
    private final StoreService storeService;

    // --- 구역(Region) 관련 API ---

    //구역 목록 조회 (검색어 필터링 및 페이징 적용)
    @GetMapping("/regions")
    public CommonResponse<RegionResponseDTO.RegionListDTO> getRegionList(
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return CommonResponse.success("구역 목록 조회 성공", regionService.getRegionList(keyword, page));
    }

    //구역 생성
    @PostMapping("/regions")
    public CommonResponse<Long> createRegion(@RequestBody @Valid RegionRequestDTO request) {
        return CommonResponse.success("구역 등록 성공", regionService.createRegion(request));
    }

    // ---  가게(Store) 관련 API ---

    @GetMapping("")
    public CommonResponse<List<StoreResponse>> getStoreList(
            @RequestParam(value = "category", required = false) String category) {
        return CommonResponse.success("가게 목록 조회 성공", null);
    }

    @GetMapping("/{storeId}")
    public CommonResponse<StoreResponse> getStore(@PathVariable Long storeId) {
        return CommonResponse.success("가게 상세 정보 조회 성공", null);
    }

    @GetMapping("/{storeId}/reviews")
    public CommonResponse<List<StoreResponse>> getStoreReviews(@PathVariable Long storeId) {
        return CommonResponse.success("가게 리뷰 목록 조회 성공", null);
    }

    @GetMapping("/{storeId}/missions")
    public CommonResponse<List<MissionPreviewResponse>> getStoreMissions(@PathVariable Long storeId) {
        return CommonResponse.success("가게 미션 목록 조회 성공", null);
    }
    @PostMapping("")
    public CommonResponse<StoreResponse> createStore(@RequestBody @Valid StoreCreateRequest request) {
        StoreResponse result = storeService.createStore(request);
        return CommonResponse.success("가게 등록 성공", result);
    }

    @PatchMapping("/{storeId}")
    public CommonResponse<StoreResponse> updateStore(
            @PathVariable Long storeId,
            @RequestBody @Valid StoreUpdateRequest request) {
        return CommonResponse.success("가게 정보 수정 성공", null);
    }

    @DeleteMapping("/{storeId}")
    public CommonResponse<Void> deleteStore(@PathVariable Long storeId) {
        return CommonResponse.success("가게 삭제 성공", null);
    }
}