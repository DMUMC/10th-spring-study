package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Slice<Review> findByIdAndIdLessThanOrderByIdDesc(Long id, long idCursor, PageRequest pageRequest);

    Slice<Review> findByIdOrderByIdDesc(Long id, PageRequest pageRequest);

    Slice<Review> findByIdOrderByStarDesc(@NotNull(message = "멤버 아이디 입력은 필수입니다.") Long id, int starCursor, long idCursor, PageRequest pageRequest);
}
