package com.UmcSpringStudy.jingjing2.domain.review.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
@lombok.Data
@Entity
public class StoreReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String title;
    private String context;
    private Double rate;
    private Integer popularity;
    private LocalDate createdDate;


    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "store_id", nullable = false)
    private Long storeId;
}
