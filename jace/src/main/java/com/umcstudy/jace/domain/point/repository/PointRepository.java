package com.umcstudy.jace.domain.point.repository;

import com.umcstudy.jace.domain.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
