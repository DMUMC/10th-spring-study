package com.UmcSpringStudy.jingjing2.domain.store.repository;

import com.UmcSpringStudy.jingjing2.domain.store.entity.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {

}