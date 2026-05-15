package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// 7. UserInterest
@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
}
