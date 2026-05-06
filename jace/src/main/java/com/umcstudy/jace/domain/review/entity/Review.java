package com.umcstudy.jace.domain.review.entity;

import com.umcstudy.jace.domain.mission.entity.Shop;
import com.umcstudy.jace.domain.review.entity.mapping.ReviewImage;
import com.umcstudy.jace.domain.review.entity.mapping.ReviewReply;
import com.umcstudy.jace.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "review_title", nullable = false, length = 40)
    private String reviewTitle;

    @Column(name = "review_content", nullable = false, length = 500)
    private String reviewContent;

    @Column(name = "review_content_time", nullable = false)
    private LocalDateTime reviewContentTime;

    @Column(name = "review_score", nullable = false, precision = 2, scale = 1)
    private BigDecimal reviewScore;

    @Column(name = "is_disabled", nullable = false)
    private Boolean isDisabled;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewReply> reviewReplies = new ArrayList<>();
}
