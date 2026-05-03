package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.UserMission;
import com.UmcSpringStudy.jingjing2.domain.user.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 1. 특정 유저의 모든 미션 도전 내역 조회 (페이징)
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m WHERE um.user.id = :userId")
    Page<UserMission> findAllByUserId(Long userId, Pageable pageable);

    // 2. 특정 유저의 미션 중 특정 상태(진행중, 완료 등)만 조회 (페이징)
    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m " +
            "WHERE um.user.id = :userId AND um.isComplete = :isComplete")
    Page<UserMission> findAllByUserIdAndIsComplete(Long userId, MissionStatus isComplete, Pageable pageable);

    // 3. 유저 ID와 미션 ID를 조합하여 도전 중인 특정 미션 정보 조회 (수정/포기 시 사용)
    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}