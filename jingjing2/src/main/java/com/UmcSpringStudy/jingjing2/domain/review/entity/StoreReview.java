package com.UmcSpringStudy.jingjing2.domain.review.entity;

import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
@lombok.Data
@Entity
public class StoreReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long srid;

    private String title;
    private String context;
    private Double rate;
    private Integer popularity;
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stid")
    private Store store;
}
