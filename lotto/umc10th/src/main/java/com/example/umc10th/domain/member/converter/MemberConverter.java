package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .Id(member.getId())
                .name(member.getName())
                .build();
    }
}
