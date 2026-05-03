package com.UmcSpringStudy.jingjing2.domain.user.entity;

import com.UmcSpringStudy.jingjing2.domain.review.entity.StoreReview;
import com.UmcSpringStudy.jingjing2.domain.user.enums.Provider;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String sub; // 소셜 식별값 또는 로컬 유저 고유 식별값

    @Column(length = 100, unique = true)
    private String email; // 로컬 로그인 ID

    @Column(length = 100)
    private String password; // 암호화된 비밀번호

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Provider provider; // LOCAL, NAVER, GOOGLE

    @Column(length = 1)
    private String sex;

    @Column(length = 20)
    private String username;

    private LocalDate birth;

    @Column(length = 100)
    private String address;

    @Column(length = 13)
    private String phone;

    @Builder.Default
    private Integer point = 0;

    @Builder.Default
    @Column(name = "CM_count")
    private Integer cmCount = 0;

    private LocalDate created;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Setting setting;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Autorication autorication;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserInterest> userInterests = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<StoreReview> storeReviews = new ArrayList<>();
}