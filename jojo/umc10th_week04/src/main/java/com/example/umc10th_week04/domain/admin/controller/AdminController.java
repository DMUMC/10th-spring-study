package com.example.umc10th_week04.domain.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 인가 실패 처리 테스트용 어드민 컨트롤러
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
