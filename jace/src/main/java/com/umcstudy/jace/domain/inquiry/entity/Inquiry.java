package com.umcstudy.jace.domain.inquiry.entity;

import com.umcstudy.jace.domain.inquiry.entity.mapping.InquiryCategory;
import com.umcstudy.jace.domain.inquiry.entity.mapping.InquiryImage;
import com.umcstudy.jace.domain.inquiry.enums.InquiryState;
import com.umcstudy.jace.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inquiry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_category_id", nullable = false)
    private InquiryCategory inquiryCategory;

    @Column(name = "inquiry_title", nullable = false, length = 30)
    private String inquiryTitle;

    @Column(name = "inquiry_content", nullable = false, length = 500)
    private String inquiryContent;

    @Column(name = "inquiry_write_time", nullable = false)
    private LocalDateTime inquiryWriteTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "inquiry_state", nullable = false)
    private InquiryState inquiryState;

    @Builder.Default
    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<InquiryImage> inquiryImages = new ArrayList<>();
}
