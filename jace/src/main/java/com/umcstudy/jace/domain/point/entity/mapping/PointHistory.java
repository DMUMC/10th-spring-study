package com.umcstudy.jace.domain.point.entity.mapping;

import com.umcstudy.jace.domain.point.enums.PointHistoryType;
import com.umcstudy.jace.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "point_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "point_history_type", nullable = false)
    private PointHistoryType pointHistoryType;

    @Column(name = "point_history_origin_id")
    private Integer pointHistoryOriginId;
}
