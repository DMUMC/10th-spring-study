package com.example.umc10th_week04.domain.user.entity;

import com.example.umc10th_week04.domain.mission.entity.UserMission;
import com.example.umc10th_week04.domain.review.entity.Review;
import com.example.umc10th_week04.domain.user.enums.Gender;
import com.example.umc10th_week04.domain.user.enums.SocialType;
import com.example.umc10th_week04.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "socialType")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialType socialType = SocialType.LOCAL;

    @Column(name = "social_uid")
    private String socialUid;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "point", nullable = false)
    private Integer point;

    // 연관 관계
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserFood> userFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserMission> userMissionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();
}
