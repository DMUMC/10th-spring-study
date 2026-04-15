package com.UmcSpringStudy.jingjing2.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "IQ_File")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IQFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Inquiry inquiry;
}