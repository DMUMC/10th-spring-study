package com.umcstudy.jace.domain.mission.entity.mapping;

import com.umcstudy.jace.domain.mission.entity.Mission;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import com.umcstudy.jace.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MissionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_condition", nullable = false)
    private MissionStatus missionCondition;

    @Column(name = "mission_comp_time")
    private LocalDateTime missionCompTime;

    @Column(name = "mission_suc_code", length = 10)
    private String missionSucCode;
}
