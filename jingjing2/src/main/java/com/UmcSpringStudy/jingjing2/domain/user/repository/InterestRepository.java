package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 6. Interest
@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
}
