package com.UmcSpringStudy.jingjing2.domain.review.repository;

import com.UmcSpringStudy.jingjing2.domain.review.entity.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
    // 특정 가게의 리뷰 목록 조회
    List<StoreReview> findAllByStoreStid(Long stid);

    // 특정 유저가 작성한 리뷰 목록 조회
    List<StoreReview> findAllByUserUid(Long uid);
}