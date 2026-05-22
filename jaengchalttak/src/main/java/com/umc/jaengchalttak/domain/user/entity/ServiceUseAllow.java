package com.umc.jaengchalttak.domain.user.entity;

import com.umc.jaengchalttak.domain.user.enums.ServiceUseTitle;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "service_use_allow")
public class ServiceUseAllow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_term_of_use_allow", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isTermOfUseAllow;

    @Column(name = "term_title", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceUseTitle termTitle;

}
