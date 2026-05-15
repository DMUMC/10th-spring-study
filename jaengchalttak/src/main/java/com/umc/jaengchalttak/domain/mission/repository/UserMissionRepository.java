package com.umc.jaengchalttak.domain.mission.repository;

import com.umc.jaengchalttak.domain.mission.entity.UserMission;
import com.umc.jaengchalttak.domain.mission.entity.UserMissionId;
import com.umc.jaengchalttak.domain.mission.enums.ProgressStatus;
import com.umc.jaengchalttak.domain.user.enums.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, UserMissionId> {

    // 특정 사용자의 상태별(진행 중/완료 등) 미션 내역을 페이징하여 조회
    // 사용자 미션, 미션, 가게를 가져옴
    @Query(value = "SELECT um " +
            "FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE um.user.id = :userId " +
            "AND um.isProgress = :isProgress",
            // 카운트 쿼리: 페이징을 위한 전체 개수 산출 (불필요한 mission, store 조인 배제)
            countQuery = "SELECT count(um) FROM UserMission um " +
                    "WHERE um.user.id = :userId " +
                    "AND um.isProgress = :isProgress")
    Page<UserMission> findByUserAndProgressWithMissionAndStore(
            @Param("userId") Long userId,
            @Param("isProgress") ProgressStatus isProgress,
            Pageable pageable
    );


    // 특정 사용자의 진행 상태(isProgress)와 가게 주소(address)가 일치하는 미션 목록을 페이징 조회
    // 사용자 미션, 미션, 가게를 가져옴
    @Query(
            value = "SELECT um " +
                    "FROM UserMission um " +
                    "JOIN FETCH um.mission m " +
                    "JOIN FETCH m.store s " +
                    "WHERE um.user.id = :userId " +
                    "AND um.isProgress = :isProgress " +
                    "AND s.storeAddress = :address",
            countQuery = "SELECT count(um) " +
                    "FROM UserMission um " +
                    "JOIN um.mission m " +
                    "JOIN m.store s " +
                    "WHERE um.user.id = :userId " +
                    "AND um.isProgress = :isProgress " +
                    "AND s.storeAddress = :address"
    )
    Page<UserMission> findMissionsByAddressAndProgress(
            @Param("userId") Long userId,
            @Param("address") Address address,
            @Param("isProgress") ProgressStatus isProgress,
            Pageable pageable
    );

}

