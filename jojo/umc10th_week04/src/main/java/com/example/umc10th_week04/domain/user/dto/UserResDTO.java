package com.example.umc10th_week04.domain.user.dto;

import com.example.umc10th_week04.domain.review.entity.Review;
import com.example.umc10th_week04.domain.user.enums.Gender;
import lombok.Builder;

public class UserResDTO {

    @Builder
    public record UserInfo(
            String name,
            String email,
            int point
    ){}

    @Builder
    public record SignupInfo(
            Long id,
            String name,
            String email,
            Gender gender,
            String birth,
            String address,
            String detailAddress
    ){}
}
