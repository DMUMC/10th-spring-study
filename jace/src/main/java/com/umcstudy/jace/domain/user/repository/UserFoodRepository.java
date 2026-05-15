package com.umcstudy.jace.domain.user.repository;

import com.umcstudy.jace.domain.user.entity.mapping.UserFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}
