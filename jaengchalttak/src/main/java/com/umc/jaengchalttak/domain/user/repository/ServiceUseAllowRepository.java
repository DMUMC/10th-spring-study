package com.umc.jaengchalttak.domain.user.repository;

import com.umc.jaengchalttak.domain.user.entity.ServiceUseAllow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUseAllowRepository extends JpaRepository <ServiceUseAllow, Long> {
}
