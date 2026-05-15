package com.umcstudy.jace.domain.user.repository;

import com.umcstudy.jace.domain.user.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
