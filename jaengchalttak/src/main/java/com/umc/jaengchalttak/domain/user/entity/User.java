package com.umc.jaengchalttak.domain.user.entity;

import com.umc.jaengchalttak.domain.inquiry.entity.Inquiry;
import com.umc.jaengchalttak.domain.mission.entity.UserMission;
import com.umc.jaengchalttak.domain.store.entity.StoreReview;
import com.umc.jaengchalttak.domain.user.enums.Address;
import com.umc.jaengchalttak.domain.user.enums.Gender;
import com.umc.jaengchalttak.domain.user.enums.SocialProvider;
import com.umc.jaengchalttak.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(nullable = false)
    private LocalDate birthDay;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    private Integer phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;

    @Column(nullable = false, length = 255)
    private String socialUid;

    @Column(nullable = false)
    @Builder.Default
    private Integer point = 0;

    // ====== 연관관계 매핑 ======
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserMission> userMissionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<FavoriteFood> favoriteFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ServiceUseAllow> termList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<StoreReview> storeReviewList = new ArrayList<>();

}
