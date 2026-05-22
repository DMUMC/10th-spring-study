package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findByMemberIdAndIdLessThanOrderByIdDesc(
            Long memberId, Long idCursor, Pageable pageable);

    @Query("""
    SELECT r FROM Review r
    WHERE r.member.id = :memberId
      AND (r.star < :starCursor
        OR (r.star = :starCursor AND r.id < :idCursor))
    ORDER BY r.star DESC, r.id DESC
    """)
    Slice<Review> findByMemberIdOrderByStarDesc(
            @Param("memberId") Long memberId,
            @Param("starCursor") int starCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable);
}
