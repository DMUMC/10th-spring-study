package com.umc.jaengchalttak.domain.user.converter;

import com.umc.jaengchalttak.domain.user.dto.UserInfoDTO;
import com.umc.jaengchalttak.domain.user.entity.ServiceUseAllow;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseTitle;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;

import java.util.Map;
import java.util.stream.Collectors;

public class UserConverter {

    // 객체 생성하면 예외
    private UserConverter() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    public static UserInfoDTO toUserInfoDTO(User user) {
        Map<ServiceUseTitle, Boolean> serviceUseAllowMap = user.getServiceUseAllows().stream()
                .collect(Collectors.toMap(
                        ServiceUseAllow::getTermTitle,
                        ServiceUseAllow::getIsTermOfUseAllow,
                        (existing, replacement) -> existing // 중복 키 발생 시 기존 값 유지 (방어적 로직)
                ));

        return UserInfoDTO.builder()
                .serviceUseAllow(serviceUseAllowMap)
                .name(user.getName())
                .gender(user.getGender())
                .birthday(user.getBirthDay())
                .address(user.getAddress().getValue())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }
}
