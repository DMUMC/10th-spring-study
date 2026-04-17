package com.UmcSpringStudy.jingjing2.domain.store.repository;

import com.UmcSpringStudy.jingjing2.domain.store.entity.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
    // 특정 가게의 모든 이미지 조회
    List<StoreImage> findAllByStoreStid(Long stid);
}