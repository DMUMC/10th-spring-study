package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

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
}
