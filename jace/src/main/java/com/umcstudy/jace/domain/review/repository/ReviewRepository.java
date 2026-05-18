package com.umcstudy.jace.domain.review.repository;

import com.umcstudy.jace.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r " +
           "JOIN FETCH r.user " +
           "WHERE r.shop.id = :shopId " +
           "AND r.isDisabled = false " +
           "AND (:cursorId IS NULL OR r.id < :cursorId) " +
           "ORDER BY r.id DESC")
    List<Review> findByShopIdWithCursor(
            @Param("shopId") Long shopId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @Query("SELECT r FROM Review r " +
           "JOIN FETCH r.user " +
           "WHERE r.user.id = :userId " +
           "AND r.isDisabled = false " +
           "AND (:cursorId IS NULL OR r.id < :cursorId) " +
           "ORDER BY r.id DESC")
    List<Review> findByUserIdOrderByIdWithCursor(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @Query("SELECT r FROM Review r " +
           "JOIN FETCH r.user " +
           "WHERE r.user.id = :userId " +
           "AND r.isDisabled = false " +
           "AND (:cursorId IS NULL " +
           "     OR r.reviewScore < (SELECT r2.reviewScore FROM Review r2 WHERE r2.id = :cursorId) " +
           "     OR (r.reviewScore = (SELECT r2.reviewScore FROM Review r2 WHERE r2.id = :cursorId) AND r.id < :cursorId)) " +
           "ORDER BY r.reviewScore DESC, r.id DESC")
    List<Review> findByUserIdOrderByScoreWithCursor(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
