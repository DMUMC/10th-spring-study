package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.*;
import com.UmcSpringStudy.jingjing2.domain.user.enums.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// 1. User
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email); // 로컬 로그인용
    Optional<User> findByProviderAndSub(Provider provider, String sub); // 소셜 로그인용
}

