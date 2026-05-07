package com.umcstudy.jace.domain.mission.repository;

import com.umcstudy.jace.domain.mission.entity.mapping.MissionUser;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionUserRepository extends JpaRepository<MissionUser, Long> {
    long countByUser_IdAndMissionCondition(Long userId, MissionStatus missionCondition);
}
