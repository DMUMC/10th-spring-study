package com.umcstudy.jace.domain.mission.repository;

import com.umcstudy.jace.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
           "JOIN FETCH m.shop s " +
           "JOIN FETCH s.shopCategory " +
           "WHERE s.shopAddress LIKE %:region% " +
           "AND (:cursorId IS NULL OR m.id < :cursorId)")
    Slice<Mission> findByRegionWithCursor(
            @Param("region") String region,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
