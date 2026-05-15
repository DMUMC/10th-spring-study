package com.example.umc10th_week04.domain.user.controller;

import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th_week04.domain.user.service.UserService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 토큰을 가져올 수 없으므로 요청 URL로 유저 id 받기
    @GetMapping("/{id}")
    public ApiResponse<UserResDTO.UserInfo> getUserInfo(
            @PathVariable Long id
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getUserInfo(id));
    }

//    @PostMapping("/signup")
//    public ApiResponse<UserResDTO.SignupInfo> signupInfo(
//            @RequestBody UserResDTO.SignupInfo dto
//    ){
//        BaseSuccessCode code = UserSuccessCode.OK;
//        return ApiResponse.onSuccess(code, userService.signupInfo(dto));
//    }
}
