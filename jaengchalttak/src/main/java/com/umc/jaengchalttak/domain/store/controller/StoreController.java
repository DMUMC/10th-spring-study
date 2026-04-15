package com.umc.jaengchalttak.domain.store.controller;

import com.umc.jaengchalttak.domain.store.dto.response.StoreInfoResponseDTO;
import com.umc.jaengchalttak.domain.store.dto.response.AreaStoreListResponseDTO;
import com.umc.jaengchalttak.domain.store.payload.code.StoreSuccessCode;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    // ====== 현재 지역 내 가게 조회 ======
    @GetMapping
    public ApiResponse<List<AreaStoreListResponseDTO>> searchAreaListStore(@RequestParam("address") String address,
                                                                           @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<AreaStoreListResponseDTO> result = List.of(
                AreaStoreListResponseDTO.builder()
                        .storeId(1L)
                        .missionPoint(500)
                        .storeName("스타벅스 강남점")
                        .storeAddress("서울 강남구 테헤란로 123")
                        .storeType("카페")
                        .storeSavePath("경로")
                        .build()
        );

        BaseSuccessCode code = StoreSuccessCode.STORE_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 가게 정보 상세 조회 ======
    @GetMapping("{storeId}")
    public ApiResponse<StoreInfoResponseDTO> getStoreInfo(@PathVariable Long storeId) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        StoreInfoResponseDTO result = StoreInfoResponseDTO.builder()
                .storeName("스타벅스 강남점")
                .storeType("카페")
                .isOpen("OPEN")
                .storeStar(4.5)
                .storeAddress("서울 강남구 테헤란로 123")
                .storeSavePath(new String[]{"경로", "경로2"})
                .build();

        BaseSuccessCode code = StoreSuccessCode.STORE_INFO_OK;
        return ApiResponse.onSuccess(code, result);
    }

}
