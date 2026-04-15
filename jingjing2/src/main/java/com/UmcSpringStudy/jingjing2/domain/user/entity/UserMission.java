package com.UmcSpringStudy.jingjing2.domain.user.entity;

import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import com.UmcSpringStudy.jingjing2.domain.user.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_Mission")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long umid;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_complete", columnDefinition = "VARCHAR(15)")
    private MissionStatus isComplete; // enum: COMPLETE, PROGRESS, FAIL

    private Integer progress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Mission mission;
}