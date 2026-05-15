package com.umc.jaengchalttak.domain.mission.entity;

import com.umc.jaengchalttak.domain.mission.enums.CompleteStatus;
import com.umc.jaengchalttak.domain.mission.enums.ProgressStatus;
import com.umc.jaengchalttak.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
// 사용자의 미션 상태 (진행 중, 진행 완료) 조회를 위한 index
@Table(
        name = "user_mission",
        indexes = {
                @Index(name = "idx_user_mission_user_progress", columnList = "user_id, is_progress")
        }
)@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@IdClass(UserMissionId.class) // 복합 키 매핑
@EntityListeners(AuditingEntityListener.class)
public class UserMission {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column
    private CompleteStatus isComplete;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgressStatus isProgress;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDate missionCompleteTime;

}
