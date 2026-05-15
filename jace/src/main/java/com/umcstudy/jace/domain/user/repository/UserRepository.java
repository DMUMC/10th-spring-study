package com.umcstudy.jace.domain.user.repository;

import com.umcstudy.jace.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
