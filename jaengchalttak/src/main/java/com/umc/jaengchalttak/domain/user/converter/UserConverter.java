package com.umc.jaengchalttak.domain.user.converter;

import com.umc.jaengchalttak.domain.user.dto.UserInfoDTO;
import com.umc.jaengchalttak.domain.user.dto.request.SignUpReqDTO;
import com.umc.jaengchalttak.domain.user.entity.FavoriteFood;
import com.umc.jaengchalttak.domain.user.entity.ServiceUseAllow;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseTitle;
import com.umc.jaengchalttak.domain.user.enums.SocialProvider;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserConverter {

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
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();
    }

    public static User toUser(SignUpReqDTO request, String encodedPassword) {
        return User.builder()
                .email(request.email())
                .name(request.name())
                .password(encodedPassword)
                .gender(request.gender())
                .birthDay(request.birthday())
                .address(request.address())
                .phoneNumber(Integer.parseInt(request.phoneNumber().replace("-", "")))
                .socialProvider(SocialProvider.LOCAL)
                .build();
    }

    public static List<ServiceUseAllow> toServiceUseAllows(SignUpReqDTO request, User user) {
        return request.serviceUseAllow().entrySet().stream()
                .map(entry -> ServiceUseAllow.builder()
                        .user(user)
                        .isTermOfUseAllow(entry.getValue())
                        .termTitle(entry.getKey())
                        .build())
                .toList();
    }

    public static List<FavoriteFood> toFavoriteFoods(SignUpReqDTO request, User user) {
        return request.favoriteFoods().stream()
                .map(foodName -> FavoriteFood.builder()
                        .user(user)
                        .name(foodName)
                        .build())
                .toList();
    }

}

