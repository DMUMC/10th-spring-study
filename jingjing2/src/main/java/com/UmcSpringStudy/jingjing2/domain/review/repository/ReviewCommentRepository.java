package com.UmcSpringStudy.jingjing2.domain.review.repository;

import com.UmcSpringStudy.jingjing2.domain.review.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

}