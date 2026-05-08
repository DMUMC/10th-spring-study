package com.umcstudy.jace.domain.inquiry.entity.mapping;

import com.umcstudy.jace.domain.inquiry.entity.Inquiry;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inquiry_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InquiryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private Inquiry inquiry;

    @Column(name = "inquiry_image_url", nullable = false, length = 500)
    private String inquiryImageUrl;
}
