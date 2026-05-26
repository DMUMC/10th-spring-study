package com.umc.jaengchalttak.domain.user.repository;

import com.umc.jaengchalttak.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.umc.jaengchalttak.domain.user.enums.SocialProvider;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndSocialProvider(String email, SocialProvider socialProvider);

    Optional<User> findBySocialProviderAndSocialUid(SocialProvider socialProvider, String socialUid);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.serviceUseAllows WHERE u.id = :id")
    Optional<User> findByIdWithServiceUseAllow(@Param("id") Long id);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
