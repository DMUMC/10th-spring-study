package com.umc.jaengchalttak.domain.store.repository;

import com.umc.jaengchalttak.domain.store.entity.OwnerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerCommentRepository extends JpaRepository<OwnerComment, Long> {
}
