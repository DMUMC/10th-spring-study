package com.umcstudy.jace.domain.user.controller;

import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.domain.user.exception.code.UserSuccessCode;
import com.umcstudy.jace.domain.user.service.UserService;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/auth/signup")
    public ApiResponse<UserResDTO.PostSignup> postSignup(
            @RequestBody UserReqDTO.PostSignup dto
    ){
        BaseSuccessCode code = UserSuccessCode.SignupOK;
        return ApiResponse.onSuccess(code, userService.postSignup(dto));
    }

    @GetMapping("/v1/terms")
    public ApiResponse<UserResDTO.GetTerms> getTerms(){
        BaseSuccessCode code = UserSuccessCode.TermsListOK;
        return ApiResponse.onSuccess(code, userService.getTerms());
    }

    @GetMapping("/v1/foods")
    public ApiResponse<UserResDTO.GetFoods> getFoods(){
        BaseSuccessCode code = UserSuccessCode.FoodsListOK;
        return ApiResponse.onSuccess(code, userService.getFoods());
    }
}
