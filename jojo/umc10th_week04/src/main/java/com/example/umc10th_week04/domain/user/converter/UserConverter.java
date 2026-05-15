package com.example.umc10th_week04.domain.user.converter;

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
}
