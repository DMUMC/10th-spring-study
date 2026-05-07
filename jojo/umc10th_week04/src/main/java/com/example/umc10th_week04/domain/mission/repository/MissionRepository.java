package com.example.umc10th_week04.domain.mission.repository;

import com.example.umc10th_week04.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
