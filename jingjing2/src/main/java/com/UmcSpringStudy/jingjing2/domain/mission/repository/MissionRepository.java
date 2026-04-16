package com.UmcSpringStudy.jingjing2.domain.mission.repository;

import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

}