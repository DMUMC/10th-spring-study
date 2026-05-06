package com.umcstudy.jace.domain.mission.entity;

import com.umcstudy.jace.domain.mission.entity.mapping.MissionUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "mission_name", length = 100)
    private String missionName;

    @Column(name = "mission_point")
    private Integer missionPoint;

    @Column(name = "mission_create_time")
    private LocalDateTime missionCreateTime;

    @Column(name = "mission_pay")
    private Integer missionPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Builder.Default
    @OneToMany(mappedBy = "mission")
    private List<MissionUser> missionUsers = new ArrayList<>();
}
