package com.example.umc10th_week04.domain.user.dto;

import lombok.Builder;

public class UserResDTO {

    @Builder
    public record SignupInfo(
            String name,
            String gender,
            String birth,
            String address,
            String detailAddress
    ){}
}
