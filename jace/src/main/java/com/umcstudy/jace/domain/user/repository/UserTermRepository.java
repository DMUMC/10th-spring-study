package com.umcstudy.jace.domain.user.repository;

import com.umcstudy.jace.domain.user.entity.mapping.UserTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermRepository extends JpaRepository<UserTerm, Long> {
}
