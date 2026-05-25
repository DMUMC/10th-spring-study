package com.UmcSpringStudy.jingjing2.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Autorication {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    // --- [필수 동의] ---
    private Boolean overFourteen;   // 만 14세 이상
    private Boolean termsOfService; // 서비스 이용약관
    private Boolean privacyPolicy;  // 개인 정보 처리 방침

    // --- [선택 동의] ---
    private Boolean locAllow;       // 위치정보 제공
    private Boolean adAllow;        // 마케팅 수신 동의
}