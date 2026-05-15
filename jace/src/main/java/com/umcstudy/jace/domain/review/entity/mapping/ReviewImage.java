package com.umcstudy.jace.domain.review.entity.mapping;

import com.umcstudy.jace.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(name = "review_image_url", nullable = false, length = 500)
    private String reviewImageUrl;
}
