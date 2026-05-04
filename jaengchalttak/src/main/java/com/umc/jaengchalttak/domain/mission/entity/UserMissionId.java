package com.umc.jaengchalttak.domain.mission.entity;

import lombok.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserMissionId implements Serializable {
    private Long user;    // User 엔티티의 @Id 필드명과 일치
    private Long mission; // Mission 엔티티의 @Id 필드명과 일치
}
