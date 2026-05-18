package com.UmcSpringStudy.jingjing2.domain.review.repository;

import com.UmcSpringStudy.jingjing2.domain.review.entity.StoreReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {

    // 1. ID순 커서 기반 조회 (최신순)
    @Query("SELECT r FROM StoreReview r WHERE r.user.id = :userId AND (:cursor IS NULL OR r.id < :cursor) ORDER BY r.id DESC")
    Slice<StoreReview> findMyReviewsByIdCursor(Long userId, Long cursor, Pageable pageable);

    // 2. 별점순 커서 기반 조회
    // 별점이 같을 경우를 대비해 ID를 두 번째 정렬 기준으로 사용
    @Query("SELECT r FROM StoreReview r WHERE r.user.id = :userId " +
            "AND (:cursorRate IS NULL OR r.rate < :cursorRate OR (r.rate = :cursorRate AND r.id < :cursorId)) " +
            "ORDER BY r.rate DESC, r.id DESC")
    Slice<StoreReview> findMyReviewsByRateCursor(Long userId, Double cursorRate, Long cursorId, Pageable pageable);
}