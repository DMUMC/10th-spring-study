package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String email,
            String phoneNumber,
            String profileUrl,
            Integer point
    ) {}

    @Builder
    public record Login(
            String accessToken
    ){}
}
