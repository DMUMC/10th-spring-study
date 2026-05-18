package com.umc.jaengchalttak.domain.user.controller;

import com.umc.jaengchalttak.domain.user.dto.request.LoginReqDTO;
import com.umc.jaengchalttak.domain.user.dto.request.SignUpReqDTO;
import com.umc.jaengchalttak.domain.user.service.AuthService;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증 API", description = "로그인, 회원가입 등 인증 관련 API입니다.")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "일반 회원가입", description = "새로운 유저 정보를 등록하여 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ApiResponse<String> signUpUser(@Valid @RequestBody SignUpReqDTO request) {
        authService.createUser(request);

        BaseSuccessCode code = UserSuccessCode.USER_CREATED;
        return ApiResponse.onSuccess(code, "회원가입 성공!");
    }

}
