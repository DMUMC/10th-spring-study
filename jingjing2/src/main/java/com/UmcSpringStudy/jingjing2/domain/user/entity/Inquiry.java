package com.UmcSpringStudy.jingjing2.domain.user.entity;

import com.UmcSpringStudy.jingjing2.domain.user.enums.InquiryType;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @Column(length = 30)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String context;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private InquiryType category;

    @Column(columnDefinition = "TEXT")
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<IQFile> iqFiles = new ArrayList<>();
}