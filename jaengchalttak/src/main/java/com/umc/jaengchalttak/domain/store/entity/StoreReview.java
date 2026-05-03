package com.umc.jaengchalttak.domain.store.entity;

import com.umc.jaengchalttak.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store_review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StoreReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Double reviewStar;

    @Column(nullable = false, length = 500)
    private String reviewContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "storeReview", cascade = CascadeType.REMOVE)
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();

    @OneToOne(mappedBy = "storeReview", cascade = CascadeType.REMOVE)
    private OwnerComment ownerComment;

    @Builder
    private StoreReview(User user, Store store, Double reviewStar, String reviewContent) {
        this.user = user;
        this.store = store;
        this.reviewStar = reviewStar;
        this.reviewContent = reviewContent;
    }


}