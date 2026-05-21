package com.example.umc10th_week04.domain.user.repository;

import com.example.umc10th_week04.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameAndDeletedAtIsNull(String name);

    @Query(value = "SELECT u FROM User u WHERE u.name = :name AND u.deletedAt IS NULL")
    Optional<User> findActiveUser(String name);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String username);
}
