package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResDTO.GetInfo GetInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }


    public void signup(MemberReqDTO.Signup dto) {
        Member member = Member.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        memberRepository.save(member);
    }
}