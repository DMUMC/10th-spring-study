package com.umcstudy.jace.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_setting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserSetting {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "new_event_alarm")
    private Boolean newEventAlarm;

    @Column(name = "review_answer_alarm")
    private Boolean reviewAnswerAlarm;

    @Column(name = "inquiry_answer_alarm")
    private Boolean inquiryAnswerAlarm;
}
