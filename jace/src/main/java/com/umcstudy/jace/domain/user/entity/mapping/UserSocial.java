package com.umcstudy.jace.domain.user.entity.mapping;

import com.umcstudy.jace.domain.user.entity.User;
import com.umcstudy.jace.domain.user.enums.SocialProvider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialProvider provider;

    @Column(name = "provider_user_id", nullable = false, length = 50)
    private String providerUserId;
}
