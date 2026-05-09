package com.example.umc10th_week04.domain.view.home.controller;

import com.example.umc10th_week04.domain.view.home.dto.HomeReqDTO;
import com.example.umc10th_week04.domain.view.home.dto.HomeResDTO;
import com.example.umc10th_week04.domain.view.home.service.HomeService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th_week04.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/{userId}")
    public ApiResponse<HomeResDTO.GetInfo> getHome(
            @PathVariable Long userId,
            @RequestParam String location
    ){
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, homeService.getHome(userId, location));
    }
}
