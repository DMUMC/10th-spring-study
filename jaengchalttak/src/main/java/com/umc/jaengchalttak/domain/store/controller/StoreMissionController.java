package com.umc.jaengchalttak.domain.store.controller;

import com.umc.jaengchalttak.domain.store.dto.request.CreateStoreMissionReqDTO;
import com.umc.jaengchalttak.domain.store.dto.response.GetStoreMissionResDTO;
import com.umc.jaengchalttak.domain.store.payload.code.StoreSuccessCode;
import com.umc.jaengchalttak.domain.store.service.StoreMissionService;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.global.dto.OffsetPagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store/mission")
@Tag(name = "가게 미션 API", description = "가게 미션 관련 API입니다.")
public class StoreMissionController {

    private final StoreMissionService storeMissionService;

    @Operation(summary = "가게 미션 생성", description = "가게 ID를 통해 해당 가게의 미션을 생성합니다.")
    @PostMapping("/{storeId}")
    public ApiResponse<Void> createStoreMission(@PathVariable Long storeId,
                                                @Valid @RequestBody CreateStoreMissionReqDTO request) {
        storeMissionService.createMission(storeId, request);
        BaseSuccessCode code = StoreSuccessCode.STORE_MISSION_CREATED_OK;
        return ApiResponse.onSuccess(code, null);
    }

    @Operation(summary = "가게 미션 조회", description = "가게 내의 미션들을 페이징하여 조회합니다.")
    @GetMapping
    public ApiResponse<OffsetPagination<GetStoreMissionResDTO>> getStoreMissions(@RequestParam Long storeId,
                                                                           @RequestParam Integer pageSize,
                                                                           @RequestParam Integer pageNumber,
                                                                           @RequestParam(required = false) String sort) {
        OffsetPagination<GetStoreMissionResDTO> result =
                storeMissionService.getMissions(storeId, pageSize, pageNumber, sort);
        BaseSuccessCode code = StoreSuccessCode.STORE_MISSION_OK;
        return ApiResponse.onSuccess(code, result);
    }

}
