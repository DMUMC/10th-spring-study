package com.example.umc10th_week04.domain.mission.repository;

import com.example.umc10th_week04.domain.mission.entity.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
            SELECT um
            FROM UserMission um
            JOIN FETCH um.mission m
            JOIN FETCH m.store s
            WHERE um.user.id = :userId
            """)
    List<UserMission> findUserMissionsByUserId(Long userId, Pageable pageable);

    @Query("""
            SELECT um
            FROM UserMission um
            JOIN FETCH um.mission m
            JOIN FETCH m.store s
            WHERE um.user.id = :userId
              AND um.completed = true
              AND s.location = :location
            """)
    List<UserMission> findCompletedMissionsByUserIdAndLocation(Long userId, String location, Pageable pageable);
}
