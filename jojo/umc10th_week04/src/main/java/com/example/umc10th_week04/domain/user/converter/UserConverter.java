package com.example.umc10th_week04.domain.user.converter;

import com.example.umc10th_week04.domain.user.dto.UserReqDTO;
import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.entity.User;

import java.util.Optional;

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

    // 확인용 컨버터
    public static UserResDTO.SignupInfo toSignupInfo(User user){
        return UserResDTO.SignupInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .birth(user.getBirth())
                .address(user.getAddress())
                .detailAddress(user.getDetailAddress())
                .build();
    }
}
