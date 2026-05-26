package com.example.umc10th_week04.domain.user.converter;

import com.example.umc10th_week04.domain.user.dto.UserReqDTO;
import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.enums.Gender;
import com.example.umc10th_week04.global.security.dto.OAuthDTO;

public class UserConverter {

    public static UserResDTO.UserInfo toUserInfo(User user){
        return UserResDTO.UserInfo.builder()
                .name(user.getName())
                .email(user.getEmail())
                .point(user.getPoint())
                .build();
    }

    public static User toUser(
            UserReqDTO.SignupInfo signupInfo,
            String encodedPassword
    ){
        return User.builder()
                .name(signupInfo.name())
                .email(signupInfo.email())
                .password(encodedPassword)
                .gender(signupInfo.gender())
                .birth(signupInfo.birth())
                .address(signupInfo.address())
                .detailAddress(signupInfo.detailAddress())
                .point(0)
                .build();
    }

    public static User toUser(
            OAuthDTO dto
    ) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .password("")
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .gender(Gender.NONE)
                .birth("")
                .address("")
                .detailAddress("")
                .point(0)
                .build();
    }

    // 확인용 컨버터
    public static UserResDTO.Signup toSignup(String accessToken){
        return UserResDTO.Signup.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }

    // 로그인 컨버터
    public static UserResDTO.Login toLogin(String accessToken) {
        return UserResDTO.Login.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }
}
