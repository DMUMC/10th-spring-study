package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class MemberReqDTO {

    public record GetInfo(
            @NotNull(message = "멤버 아이디 입력은 필수입니다")
            Long id
    )
    {}

    public record Signup(
            String name,
            String password,
            Address address,
            String detail_address,
            String birthday,
            Gender gender,
            SocialType socialType,
//          String uid,
            Integer point,
            String profileUrl,
            String phoneNumber,
            String email
    ){}

    public record Login(
            @NotNull(message = "이메일 입력은 필수입니다")
            String email,
            @NotNull(message = "비밀번호 입력은 필수입니다")
            String password
    ){}
}
