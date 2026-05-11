package com.umc.jaengchalttak.domain.mission.repository;

import com.umc.jaengchalttak.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository <Mission, Long> {
    Page<Mission> findAllByStoreId(Long storeId, PageRequest pageRequest);
}
