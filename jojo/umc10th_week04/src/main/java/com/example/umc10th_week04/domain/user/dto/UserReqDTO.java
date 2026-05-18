package com.example.umc10th_week04.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public class UserReqDTO {

    public record SignupInfo(

            @NotNull(message = "유저 이름은 필수 입니다.")
            String name,

            @NotNull(message = "유저 성별은 필수 입니다.")
            String gender,

            @NotNull(message = "유저 생년월일은 필수 입니다.")
            String birth,

            @NotNull(message = "유저 주소는 필수 입니다.")
            String address,

            @NotNull(message = "유저 상세 주소는 필수 입니다.")
            String detailAddress
    ){}
}
