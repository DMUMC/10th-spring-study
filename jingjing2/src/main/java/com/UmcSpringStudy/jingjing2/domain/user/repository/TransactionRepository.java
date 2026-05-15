package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// 2. Transaction
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
