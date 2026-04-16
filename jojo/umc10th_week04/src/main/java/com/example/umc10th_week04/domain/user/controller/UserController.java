package com.example.umc10th_week04.domain.user.controller;

import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.service.UserService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResDTO.SignupInfo> signupInfo(
            @RequestBody UserResDTO.SignupInfo dto
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.signupInfo(dto))
    }
}
