package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            Long Id,
            String name
    ){}
}
