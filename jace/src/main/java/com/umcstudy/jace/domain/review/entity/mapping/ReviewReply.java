package com.umcstudy.jace.domain.review.entity.mapping;

import com.umcstudy.jace.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReviewReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(name = "review_comment_cont", nullable = false, length = 500)
    private String reviewCommentContent;

    @Column(name = "review_comment_time", nullable = false)
    private LocalDateTime reviewCommentTime;
}
