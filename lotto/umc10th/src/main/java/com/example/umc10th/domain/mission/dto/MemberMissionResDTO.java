package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.entity.Mission;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {
    @Builder
    public record GetInfo(
            Mission missionId,
            String title,
            Integer point,
            String storeName,
            String information,
            LocalDateTime deadline
    ){}
}
