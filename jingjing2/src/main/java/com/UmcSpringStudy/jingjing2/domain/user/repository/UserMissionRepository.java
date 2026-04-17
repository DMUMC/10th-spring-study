package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 3. UserMission
@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    List<UserMission> findAllByUserUid(Long uid);
}
