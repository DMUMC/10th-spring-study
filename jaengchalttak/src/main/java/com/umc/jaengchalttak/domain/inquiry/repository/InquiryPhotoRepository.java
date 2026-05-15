package com.umc.jaengchalttak.domain.inquiry.repository;

import com.umc.jaengchalttak.domain.inquiry.entity.InquiryPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryPhotoRepository extends JpaRepository<InquiryPhoto, Long> {
}
