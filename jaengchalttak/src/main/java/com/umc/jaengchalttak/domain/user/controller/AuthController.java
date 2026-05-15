package com.umc.jaengchalttak.domain.user.controller;

import com.umc.jaengchalttak.domain.user.dto.request.LoginReqDTO;
import com.umc.jaengchalttak.domain.user.dto.UserInfoDTO;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Operation(summary = "예외 처리 테스트용 API", description = "USER_NOT_FOUND 예외를 강제로 발생시켜 GlobalExceptionAdvice 작동을 확인합니다.")
    @GetMapping("/test")
    public ApiResponse<String> test() {
        throw new UserException(UserErrorCode.USER_NOT_FOUND);
    }

    @Operation(summary = "일반 회원가입", description = "새로운 유저 정보를 등록하여 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ApiResponse<String> signUpUser(@RequestBody UserInfoDTO request) {
        BaseSuccessCode code = UserSuccessCode.USER_CREATED;
        return ApiResponse.onSuccess(code, "회원가입 성공!");
    }

    @Operation(summary = "로그인", description = "아이디와 비밀번호를 통해 인증을 진행하고 JWT 액세스 토큰을 발급합니다.")
    @PostMapping("/login")
    public ApiResponse<String> loginUser(@Valid @RequestBody LoginReqDTO request) {
        BaseSuccessCode code = UserSuccessCode.LOGIN_OK;
        return ApiResponse.onSuccess(code, "엑세스 토큰"); // 임시값, 완성되면 엑세스 토큰 넣음
    }

    @Operation(summary = "로그아웃", description = "서버 측 세션 혹은 토큰 무효화 처리를 수행합니다.")
    @PostMapping("/logout")
    public ApiResponse<String> logoutUser() {
        BaseSuccessCode code = UserSuccessCode.LOGOUT_OK;
        return ApiResponse.onSuccess(code, "로그아웃 성공.");
    }

}
