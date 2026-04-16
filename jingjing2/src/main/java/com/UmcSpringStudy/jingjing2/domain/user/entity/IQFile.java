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

    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;
}