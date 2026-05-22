package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address")
    private String detail_address;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "uid")
    private String uid;

    @Column(name = "point")
    private Integer point;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
}