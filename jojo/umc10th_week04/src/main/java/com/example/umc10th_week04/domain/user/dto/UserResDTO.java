package com.example.umc10th_week04.domain.user.dto;

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
    public record Signup(
            String accessToken,
            String tokenType
    ){}

    @Builder
    public record Login(
            String accessToken,
            String tokenType
    ) {}
}
