package com.umcstudy.jace.domain.review.repository;

import com.umcstudy.jace.domain.review.entity.mapping.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
