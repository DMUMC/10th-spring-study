package com.UmcSpringStudy.jingjing2.domain.user.repository;

import com.UmcSpringStudy.jingjing2.domain.user.entity.IQFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 9. IQFile
@Repository
public interface IQFileRepository extends JpaRepository<IQFile, Long> {
}
