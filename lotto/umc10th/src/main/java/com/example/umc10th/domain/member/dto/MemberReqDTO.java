package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import jakarta.validation.constraints.NotNull;

public class MemberReqDTO {

    public record GetInfo(
            @NotNull(message = "멤버 아이디 입력은 필수입니다")
            Long id
    )
    {};
}
