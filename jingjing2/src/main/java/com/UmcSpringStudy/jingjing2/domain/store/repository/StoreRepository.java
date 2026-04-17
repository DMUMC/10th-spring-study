package com.UmcSpringStudy.jingjing2.domain.store.repository;

import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    // 카테고리별 가게 검색
    List<Store> findAllByCategory(String category);

    // 지역별 가게 검색
    List<Store> findAllByRegion(String region);
}