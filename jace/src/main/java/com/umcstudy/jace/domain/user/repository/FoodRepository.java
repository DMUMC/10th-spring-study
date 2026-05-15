package com.umcstudy.jace.domain.user.repository;

import com.umcstudy.jace.domain.user.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
