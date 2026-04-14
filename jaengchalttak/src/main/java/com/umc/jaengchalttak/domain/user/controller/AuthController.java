package com.umc.jaengchalttak.domain.user.controller;

import com.umc.jaengchalttak.domain.user.dto.request.LoginRequestDTO;
import com.umc.jaengchalttak.domain.user.dto.UserInfoDTO;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("test")
    public ApiResponse<String> test() {
        throw new UserException(UserErrorCode.USER_NOT_FOUND);
    }

    // ====== 회원가입 ======
    @PostMapping("/signup")
    public ApiResponse<String> signUpUser(@RequestBody UserInfoDTO request) {
        BaseSuccessCode code = UserSuccessCode.USER_CREATED;
        return ApiResponse.onSuccess(code, "회원가입 성공!");
    }

    // ====== 로그인  ======
    @PostMapping("/login")
    public ApiResponse<String> loginUser(@Valid @RequestBody LoginRequestDTO request) {
        BaseSuccessCode code = UserSuccessCode.LOGIN_OK;
        return ApiResponse.onSuccess(code, "엑세스 토큰"); // 임시값, 완성되면 엑세스 토큰 넣음
    }

    // ====== 로그아웃  ======
    @PostMapping("/logout")
    public ApiResponse<String> logoutUser() {
        BaseSuccessCode code = UserSuccessCode.LOGOUT_OK;
        return ApiResponse.onSuccess(code, "로그아웃 성공.");
    }

}
