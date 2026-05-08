package com.example.umc10th_week04.domain.mission.repository;

import com.example.umc10th_week04.domain.mission.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findById(Long id);
}
