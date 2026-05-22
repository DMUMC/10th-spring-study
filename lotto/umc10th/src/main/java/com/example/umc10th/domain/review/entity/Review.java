package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "content")
    private String content;

    @Column(name = "star", precision = 2, scale = 1)
    private BigDecimal star;
}
