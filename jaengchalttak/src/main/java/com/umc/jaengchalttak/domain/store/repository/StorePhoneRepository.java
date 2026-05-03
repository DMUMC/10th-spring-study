package com.umc.jaengchalttak.domain.store.repository;

import com.umc.jaengchalttak.domain.store.entity.StorePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorePhoneRepository extends JpaRepository<StorePhoto, Long> {
}
