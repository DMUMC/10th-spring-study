package com.umc.jaengchalttak.domain.store.controller;

import com.umc.jaengchalttak.domain.store.dto.response.StoreInfoResDTO;
import com.umc.jaengchalttak.domain.store.dto.response.AreaStoreListResDTO;
import com.umc.jaengchalttak.domain.store.enums.StoreType;
import com.umc.jaengchalttak.domain.store.payload.code.StoreSuccessCode;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umc.jaengchalttak.domain.user.enums.Address.TEHERAN_RO;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Operation(summary = "현재 지역 내 가게 조회", description = "입력된 주소(지역)를 기반으로 해당 지역의 가게 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<AreaStoreListResDTO>> searchAreaListStore(@RequestParam("address") String address,
                                                                      @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<AreaStoreListResDTO> result = List.of(
                AreaStoreListResDTO.builder()
                        .storeId(1L)
                        .missionPoint(500)
                        .storeName("스타벅스 강남점")
                        .storeAddress(TEHERAN_RO)
                        .storeType(StoreType.CAFE)
                        .storeSavePath("경로")
                        .build()
        );

        BaseSuccessCode code = StoreSuccessCode.STORE_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }


    @Operation(summary = "가게 정보 상세 조회", description = "가게 ID를 통해 해당 가게의 상세 정보(상태, 별점, 주소 등)를 조회합니다.")
    @GetMapping("/{storeId}")
    public ApiResponse<StoreInfoResDTO> getStoreInfo(@PathVariable Long storeId) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        StoreInfoResDTO result = StoreInfoResDTO.builder()
                .storeName("스타벅스 강남점")
                .storeType(StoreType.CAFE)
                .isOpen("OPEN")
                .storeStar(4.5)
                .storeAddress(TEHERAN_RO)
                .storeSavePath(List.of("경로", "경로2"))
                .build();

        BaseSuccessCode code = StoreSuccessCode.STORE_INFO_OK;
        return ApiResponse.onSuccess(code, result);
    }

}
