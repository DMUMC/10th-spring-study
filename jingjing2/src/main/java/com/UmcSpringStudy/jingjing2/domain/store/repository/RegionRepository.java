package com.UmcSpringStudy.jingjing2.domain.store.repository;

import com.UmcSpringStudy.jingjing2.domain.store.entity.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    @Query("SELECT r FROM Region r WHERE r.name LIKE %:keyword%")
    Slice<Region> findByNameContaining(@Param("keyword") String keyword, Pageable pageable);
}