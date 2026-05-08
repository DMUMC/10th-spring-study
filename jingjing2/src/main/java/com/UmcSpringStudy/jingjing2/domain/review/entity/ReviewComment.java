package com.UmcSpringStudy.jingjing2.domain.review.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@lombok.Data
@Entity
public class ReviewComment {
    //가게리뷰의 답변은 다른유저가 아닌 가게에서 달아준다는 가정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String context;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private StoreReview review;
}
