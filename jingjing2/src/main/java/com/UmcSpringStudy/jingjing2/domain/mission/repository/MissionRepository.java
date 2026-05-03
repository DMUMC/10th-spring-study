package com.UmcSpringStudy.jingjing2.domain.mission.repository;

import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    //특정 구역 ID를 기반으로 모든 미션을 한 번에 조회
    @Query("SELECT m FROM Mission m JOIN m.store s WHERE s.region.id = :regionId")
    List<Mission> findAllByRegionId(@Param("regionId") Long regionId);
}