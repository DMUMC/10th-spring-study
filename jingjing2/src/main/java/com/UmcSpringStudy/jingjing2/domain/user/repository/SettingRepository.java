package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 4. Setting (User와 1:1)
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
}
