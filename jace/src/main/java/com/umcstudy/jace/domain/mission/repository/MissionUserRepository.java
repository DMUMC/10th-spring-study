package com.umcstudy.jace.domain.mission.repository;

import com.umcstudy.jace.domain.mission.entity.mapping.MissionUser;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionUserRepository extends JpaRepository<MissionUser, Long> {

    long countByUser_IdAndMissionCondition(Long userId, MissionStatus missionCondition);

    @Query(value = "SELECT mu FROM MissionUser mu " +
                   "JOIN FETCH mu.mission m " +
                   "JOIN FETCH m.shop s " +
                   "JOIN FETCH s.shopCategory " +
                   "WHERE mu.user.id = :userId " +
                   "AND mu.missionCondition = :missionCondition " +
                   "ORDER BY mu.id DESC",
           countQuery = "SELECT COUNT(mu) FROM MissionUser mu " +
                        "WHERE mu.user.id = :userId " +
                        "AND mu.missionCondition = :missionCondition")
    Page<MissionUser> findByUserIdAndCondition(
            @Param("userId") Long userId,
            @Param("missionCondition") MissionStatus missionCondition,
            Pageable pageable
    );
}
