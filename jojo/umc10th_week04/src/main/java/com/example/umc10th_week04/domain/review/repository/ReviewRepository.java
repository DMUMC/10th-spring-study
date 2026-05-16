package com.example.umc10th_week04.domain.review.repository;

import com.example.umc10th_week04.domain.review.entity.Review;
import com.example.umc10th_week04.domain.user.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findById(Long id);

    Slice<Review> findReviewsByUser_IdAAndIdLessThanOrderByIdDesc(Long userId, long idCursor, PageRequest pageRequest);

    Slice<Review> findReviewsByUser_IdOrderByDesc(Long userId, PageRequest pageRequest);
}
