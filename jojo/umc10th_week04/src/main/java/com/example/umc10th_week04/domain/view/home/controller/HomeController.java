package com.example.umc10th_week04.domain.view.home.controller;

import com.example.umc10th_week04.domain.user.dto.UserReqDTO;
import com.example.umc10th_week04.domain.view.home.dto.HomeResDTO;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/view")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/home")
    public ApiResponse<HomeResDTO.GetInfo> getHome(
        @RequestBody UserReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = HomeSuccessCode.OK;
        return ApiResponse.onSuccess(code, homeService.getHome(code));
    }
}
