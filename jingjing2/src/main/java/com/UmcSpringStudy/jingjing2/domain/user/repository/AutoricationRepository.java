package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.Autorication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 5. Autorication (User와 1:1)
@Repository
public interface AutoricationRepository extends JpaRepository<Autorication, Long> {
}
