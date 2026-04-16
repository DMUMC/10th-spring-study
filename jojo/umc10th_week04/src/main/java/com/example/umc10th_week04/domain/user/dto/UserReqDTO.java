package com.example.umc10th_week04.domain.user.dto;

public class UserReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record SignupInfo(
            String name,
            String gender,
            String birth,
            String address,
            String detailAddress
    ){}
}
