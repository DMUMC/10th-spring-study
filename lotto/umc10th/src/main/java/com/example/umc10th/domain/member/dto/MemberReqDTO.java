package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;

public class MemberReqDTO {

    public record GetInfo(SocialType socialType,
                          String uid,
                          String name,
                          Address address,
                          String detailAddress,
                          String birthday,
                          Gender gender)
    {};
}
