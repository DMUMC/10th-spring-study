package com.umc.jaengchalttak.domain.user.controller;

import com.umc.jaengchalttak.domain.user.dto.request.SignUpReqDTO;
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

    @Operation(summary = "일반 회원가입", description = "새로운 유저 정보를 등록하여 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ApiResponse<String> signUpUser(@Valid @RequestBody SignUpReqDTO request) {
        authService.createUser(request);

        BaseSuccessCode code = UserSuccessCode.USER_CREATED;
        return ApiResponse.onSuccess(code, "회원가입 성공!");
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "이메일 중복 체크", description = "true면 사용 가능한 이메일, false면 이미 있는 이메일입니다.")
    public ResponseEntity<Boolean> checkNameAvailability(@PathVariable String email) {
        return ResponseEntity.ok(!authService.isEmailDuplicated(email));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "이름 중복 체크", description = "true면 사용 가능한 이름, false면 이미 있는 이름입니다.")
    public ResponseEntity<Boolean> checkEmailAvailability(@PathVariable String name) {
        return ResponseEntity.ok(!authService.isNameDuplicated(name));
    }

}
