package com.umcstudy.jace.domain.mission.repository;

import com.umcstudy.jace.domain.mission.entity.mapping.MissionUser;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionUserRepository extends JpaRepository<MissionUser, Long> {

    long countByUser_IdAndMissionCondition(Long userId, MissionStatus missionCondition);

    @Query("SELECT mu FROM MissionUser mu " +
           "WHERE mu.user.id = :userId " +
           "AND mu.missionCondition = :missionCondition " +
           "AND (:cursorId IS NULL OR mu.id < :cursorId) " +
           "ORDER BY mu.id DESC")
    List<MissionUser> findByUserIdAndConditionWithCursor(
            @Param("userId") Long userId,
            @Param("missionCondition") MissionStatus missionCondition,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
