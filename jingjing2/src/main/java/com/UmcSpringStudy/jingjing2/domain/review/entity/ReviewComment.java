package com.UmcSpringStudy.jingjing2.domain.review.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@lombok.Data
@Entity
public class ReviewComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String context;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private StoreReview review;
}
