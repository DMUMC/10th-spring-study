package com.umc.jaengchalttak.domain.store.repository;

import com.umc.jaengchalttak.domain.store.entity.StoreReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {

    // ID 기준 페이징
    @Query("""
        SELECT r FROM StoreReview r
        JOIN FETCH r.user
        JOIN FETCH r.store
        WHERE r.user.id = :userId
        AND r.store.id = :storeId
        AND (:cursorId IS NULL OR r.id < :cursorId)
        ORDER BY r.id DESC
    """)
    Slice<StoreReview> findReviewsByIdCursor(
            @Param("userId") Long userId,
            @Param("storeId") Long storeId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 별점 기준 페이징 (별점이 같을 경우 ID를 비교)
    @Query("SELECT r FROM StoreReview r " +
            "JOIN FETCH r.user " +
            "JOIN FETCH r.store " +
            "WHERE r.user.id = :userId AND r.store.id = :storeId " +
            "AND (:cursorStar IS NULL OR " +
            "     r.reviewStar < :cursorStar OR " +
            "    (r.reviewStar = :cursorStar AND r.id < :cursorId)) " +
            "ORDER BY r.reviewStar DESC, r.id DESC")
    Slice<StoreReview> findReviewsByStarCursor(
            @Param("userId") Long userId,
            @Param("storeId") Long storeId,
            @Param("cursorStar") Double cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
