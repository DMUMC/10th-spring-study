package com.UmcSpringStudy.jingjing2.domain.mission.repository;

import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 특정 가게의 미션 목록 조회
    List<Mission> findAllByStoreStid(Long stid);
}