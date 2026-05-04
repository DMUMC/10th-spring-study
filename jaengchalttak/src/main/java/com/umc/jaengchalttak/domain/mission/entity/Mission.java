package com.umc.jaengchalttak.domain.mission.entity;

import com.umc.jaengchalttak.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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

    @Column(nullable = false, length = 50)
    private String missionName;

    @Column(nullable = false)
    private Integer missionPoint;

    @Column(nullable = false)
    private Integer missionAmount;

    @Column(nullable = false)
    private LocalDate missionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // ====== 연관관계 매핑 ======
    @Builder.Default
    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    private List<UserMission> userMissionList = new ArrayList<>();

}