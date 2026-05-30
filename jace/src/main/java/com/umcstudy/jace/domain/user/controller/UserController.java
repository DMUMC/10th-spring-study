package com.umcstudy.jace.domain.user.controller;

import com.umcstudy.jace.domain.user.controller.docs.UserControllerDocs;
import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.domain.user.exception.code.UserSuccessCode;
import com.umcstudy.jace.domain.user.service.RefreshTokenService;
import com.umcstudy.jace.domain.user.service.SocialLoginService;
import com.umcstudy.jace.domain.user.service.UserService;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController implements UserControllerDocs {

    private final UserService userService;
    private final SocialLoginService socialLoginService;
    private final RefreshTokenService refreshTokenService;

    @Override
    @PostMapping("/auth/login")
    public ApiResponse<UserResDTO.SocialLogin> socialLogin(
            @Valid @RequestBody UserReqDTO.SocialLogin dto
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.LoginOK, socialLoginService.login(dto));
    }

    @Override
    @PostMapping("/auth/token/refresh")
    public ApiResponse<UserResDTO.TokenReissue> tokenReissue(
            @Valid @RequestBody UserReqDTO.TokenReissue dto
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.LoginOK, refreshTokenService.reissue(dto.refreshToken()));
    }

    @Override
    @PostMapping("/auth/login/form")
    public ApiResponse<UserResDTO.FormLogin> formLogin(
            @Valid @RequestBody UserReqDTO.FormLogin dto
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.LoginOK, userService.formLogin(dto));
    }

    @Override
    @PostMapping("/auth/signup")
    public ApiResponse<UserResDTO.PostSignup> postSignup(
            @Valid @RequestBody UserReqDTO.PostSignup dto
    ) {
        BaseSuccessCode code = UserSuccessCode.SignupOK;
        return ApiResponse.onSuccess(code, userService.postSignup(dto));
    }

    @Override
    @PostMapping("/auth/signup/form")
    public ApiResponse<UserResDTO.PostSignup> formSignup(
            @Valid @RequestBody UserReqDTO.FormSignup dto
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.SignupOK, userService.formSignup(dto));
    }

    @Override
    @GetMapping("/myPage")
    public ApiResponse<UserResDTO.GetMyPage> getMyPage() {
        return ApiResponse.onSuccess(UserSuccessCode.MyPageOK, userService.getMyPage());
    }

    @Override
    @GetMapping("/terms")
    public ApiResponse<UserResDTO.GetTerms> getTerms() {
        BaseSuccessCode code = UserSuccessCode.TermsListOK;
        return ApiResponse.onSuccess(code, userService.getTerms());
    }

    @Override
    @GetMapping("/foods")
    public ApiResponse<UserResDTO.GetFoods> getFoods() {
        BaseSuccessCode code = UserSuccessCode.FoodsListOK;
        return ApiResponse.onSuccess(code, userService.getFoods());
    }
}
