package com.umcstudy.jace.domain.point.converter;

import com.umcstudy.jace.domain.point.entity.Point;
import com.umcstudy.jace.domain.user.entity.User;

public class PointConverter {

    public static Point toPoint(User user) {
        return Point.builder()
                .user(user)
                .pointBalance(0)
                .build();
    }
}
