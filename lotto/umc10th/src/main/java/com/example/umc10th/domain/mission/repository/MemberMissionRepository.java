package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberIdAndStatusIn(
            Long memberId,
            List<Status> status,
            Pageable pageable
    );

    @Query("""
        SELECT mm FROM MemberMission mm
        LEFT JOIN FETCH mm.mission m
        WHERE mm.member.id = :memberId
          AND mm.id < :missionId
          AND mm.status IN :status
        ORDER BY mm.id DESC
        """)
    Slice<MemberMission> findMemberMissions(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId,
            @Param("status") List<Status> status,
            Pageable pageable
    );
}