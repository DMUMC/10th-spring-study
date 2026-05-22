package com.example.umc10th_week04.domain.mission.repository;

import com.example.umc10th_week04.domain.mission.entity.Mission;
import com.example.umc10th_week04.domain.mission.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            SELECT m
            FROM Mission m
            JOIN FETCH m.store s
            WHERE s.location = :location
            """)
    List<Mission> findAvailableMissionsByLocation(String location);

    Slice<Mission> findMissionsByStoreIdAndIdLessThanOrderByIdDesc(Long storeId, long idCursor, Pageable pageRequest);

    Slice<Mission> findMissionsByStoreIdOrderByIdDesc(Long storeId, Pageable pageRequest);

    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);
}
