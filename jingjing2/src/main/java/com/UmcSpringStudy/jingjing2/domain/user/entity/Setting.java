package com.UmcSpringStudy.jingjing2.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Setting {

    @Id
    private Long uid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "uid")
    private User user;

    private Boolean eventNotice;
    private Boolean reviewNotice;
    private Boolean iqNotice;
}