package com.umcstudy.jace.domain.user.repository;

import com.umcstudy.jace.domain.user.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
}
