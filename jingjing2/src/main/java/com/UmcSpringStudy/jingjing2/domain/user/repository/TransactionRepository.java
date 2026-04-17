package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 2. Transaction
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserUid(Long uid); // 특정 유저의 거래 내역 조회
}
