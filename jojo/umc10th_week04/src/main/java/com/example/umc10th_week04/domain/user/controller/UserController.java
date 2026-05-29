package com.example.umc10th_week04.domain.user.controller;

import com.example.umc10th_week04.domain.user.dto.UserReqDTO;
import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th_week04.domain.user.service.UserService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th_week04.global.security.entity.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 토큰을 매개변수로 인증하는 로직
    @GetMapping("/v2/users/me")
    public ApiResponse<UserResDTO.UserInfo> getUserInfo(
            @AuthenticationPrincipal AuthUser user
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getUserInfo(user));
    }

    @PostMapping("login")
    public ApiResponse<UserResDTO.Login> login(
            @RequestBody @Valid UserReqDTO.Login dto
    ) {
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.login(dto));
    }

    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.Signup> signUp(
            @Valid @RequestBody UserReqDTO.SignupInfo dto
    ) {
        BaseSuccessCode code = UserSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, userService.signup(dto));
    }
}
