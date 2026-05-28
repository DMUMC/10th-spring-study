package com.umcstudy.jace.domain.user.controller.docs;

import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "사용자 관련 API")
public interface UserControllerDocs {

    @Operation(summary = "소셜 로그인", description = "카카오, 네이버, 구글, 애플 소셜 액세스 토큰으로 로그인합니다. 신규 사용자는 isNewUser=true를 반환합니다.")
    @SecurityRequirements
    ApiResponse<UserResDTO.SocialLogin> socialLogin(UserReqDTO.SocialLogin dto);

    @Operation(summary = "폼 로그인", description = "이메일과 비밀번호로 로그인합니다. 로그인 성공 시 JWT 액세스 토큰을 반환합니다.")
    @SecurityRequirements
    ApiResponse<UserResDTO.FormLogin> formLogin(UserReqDTO.FormLogin dto);

    @Operation(summary = "토큰 재발급", description = "리프레시 토큰으로 액세스 토큰과 리프레시 토큰을 재발급합니다. 리프레시 토큰은 매 요청마다 교체됩니다(Token Rotation).")
    @SecurityRequirements
    ApiResponse<UserResDTO.TokenReissue> tokenReissue(UserReqDTO.TokenReissue dto);

    @Operation(summary = "소셜 회원가입", description = "소셜 로그인 후 추가 정보를 입력해 회원가입을 완료합니다. 약관 동의, 선호 음식 선택이 필수입니다.")
    @SecurityRequirements
    ApiResponse<UserResDTO.PostSignup> postSignup(UserReqDTO.PostSignup dto);

    @Operation(summary = "폼 회원가입", description = "이메일과 비밀번호로 회원가입합니다. 비밀번호는 BCrypt로 암호화되어 저장됩니다. 이메일 중복 시 409를 반환합니다.")
    @SecurityRequirements
    ApiResponse<UserResDTO.PostSignup> formSignup(UserReqDTO.FormSignup dto);

    @Operation(summary = "마이페이지 조회", description = "JWT 토큰으로 인증된 사용자의 이름, 이메일, 포인트 잔액을 조회합니다.")
    ApiResponse<UserResDTO.GetMyPage> getMyPage();

    @Operation(summary = "약관 목록 조회", description = "회원가입 시 동의해야 할 약관 목록을 조회합니다.")
    @SecurityRequirements
    ApiResponse<UserResDTO.GetTerms> getTerms();

    @Operation(summary = "선호 음식 목록 조회", description = "회원가입 시 선택 가능한 음식 카테고리 목록을 조회합니다.")
    @SecurityRequirements
    ApiResponse<UserResDTO.GetFoods> getFoods();
}
