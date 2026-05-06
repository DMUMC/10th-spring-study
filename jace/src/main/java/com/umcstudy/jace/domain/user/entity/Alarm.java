package com.umcstudy.jace.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarm")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "alarm_name", nullable = false, length = 40)
    private String alarmName;

    @Column(name = "alarm_content", nullable = false, length = 100)
    private String alarmContent;

    @Column(name = "alarm_date", nullable = false)
    private LocalDateTime alarmDate;
}
