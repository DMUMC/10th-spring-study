package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Status;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {



    @Query("""
        SELECT m FROM Mission m
        JOIN m.store s
        LEFT JOIN MemberMission mm
            ON mm.mission.id = m.id
            AND mm.member.id = :memberId
        WHERE mm.mission.id IS NULL
          AND m.id < :missionId
          AND s.location.id = :locationId
        ORDER BY m.id DESC
        """)
    Slice<Mission> findAvailableMissions(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId,
            @Param("locationId") Long locationId,
            Pageable pageable
    );
}