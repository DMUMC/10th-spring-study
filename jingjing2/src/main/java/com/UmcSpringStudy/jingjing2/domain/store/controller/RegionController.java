package com.UmcSpringStudy.jingjing2.domain.store.controller;

import com.UmcSpringStudy.jingjing2.domain.store.dto.response.RegionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionController {

    // 지역 목록 조회 (미션 필터링용 리스트 제공)
    @GetMapping("")
    public RegionResponseDTO.RegionListDTO getRegionList() {
        // Service 호출 로직 생략
        return null;
    }

    // 특정 지역 등록 (관리자용)
    @PostMapping("")
    public String createRegion(@RequestBody String request) {
        // Service 호출 로직 생략
        return "Region Created";
    }
}