package com.UmcSpringStudy.jingjing2.domain.review.repository;

import com.UmcSpringStudy.jingjing2.domain.review.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
    // 특정 리뷰에 달린 댓글(답글) 조회
    List<ReviewComment> findAllByStoreReviewSrid(Long srid);
}