package com.umc.jaengchalttak.domain.user.repository;

import com.umc.jaengchalttak.domain.user.entity.FavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Long> {
}
