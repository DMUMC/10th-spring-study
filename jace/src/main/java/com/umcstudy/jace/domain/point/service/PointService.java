package com.umcstudy.jace.domain.point.service;

import com.umcstudy.jace.domain.point.converter.PointConverter;
import com.umcstudy.jace.domain.point.repository.PointRepository;
import com.umcstudy.jace.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    @Transactional
    public void createInitialPoint(User user) {
        pointRepository.save(PointConverter.toPoint(user));
    }

    @Transactional(readOnly = true)
    public int getPointBalance(Long userId) {
        return pointRepository.findById(userId)
                .map(point -> point.getPointBalance())
                .orElse(0);
    }
}
