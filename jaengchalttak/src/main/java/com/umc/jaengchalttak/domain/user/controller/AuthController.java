package com.umc.jaengchalttak.domain.user.controller;

import com.umc.jaengchalttak.domain.user.dto.request.LoginReqDTO;
import com.umc.jaengchalttak.domain.user.dto.request.SignUpReqDTO;
import com.umc.jaengchalttak.domain.user.dto.response.LoginResDTO;
import com.umc.jaengchalttak.domain.user.service.AuthService;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증 API", description = "로그인, 회원가입 등 인증 관련 API입니다.")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "사용자의 이메일, 비밀번호를 받고 Spring Security로 인증/인가 후 토큰을 발급합니다.")
    @PostMapping("/login")
    public ApiResponse<LoginResDTO> login(@Valid @RequestBody LoginReqDTO request) {
        LoginResDTO accessToken = authService.login(request);

        BaseSuccessCode code = UserSuccessCode.USER_CHECK_OK;
        return ApiResponse.onSuccess(code, accessToken);
    }

    @Operation(summary = "일반 회원가입", description = "새로운 유저 정보를 등록하여 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ApiResponse<String> signUpUser(@Valid @RequestBody SignUpReqDTO request) {
        authService.createUser(request);

        BaseSuccessCode code = UserSuccessCode.USER_CREATED;
        return ApiResponse.onSuccess(code, "회원가입 성공!");
    }


    @GetMapping("/email/{email}")
    @Operation(summary = "이메일 중복 체크", description = "false면 사용 가능한 이메일, true면 이미 있는 이메일입니다.")
    public ResponseEntity<Boolean> checkNameAvailability(@PathVariable String email) {
        return ResponseEntity.ok(authService.isEmailDuplicated(email));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "이름 중복 체크", description = "false면 사용 가능한 이름, true면 이미 있는 이름입니다.")
    public ResponseEntity<Boolean> checkEmailAvailability(@PathVariable String name) {
        return ResponseEntity.ok(authService.isNameDuplicated(name));
    }

}
