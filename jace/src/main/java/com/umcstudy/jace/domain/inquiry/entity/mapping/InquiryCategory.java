package com.umcstudy.jace.domain.inquiry.entity.mapping;

import com.umcstudy.jace.domain.inquiry.entity.Inquiry;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inqury_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InquiryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_category_id")
    private Long id;

    @Column(name = "inquiry_category_name", nullable = false, length = 20)
    private String inquiryCategoryName;

    @Builder.Default
    @OneToMany(mappedBy = "inquiryCategory")
    private List<Inquiry> inquiries = new ArrayList<>();
}
