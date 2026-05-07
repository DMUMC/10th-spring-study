package com.example.umc10th_week04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
public class Umc10thWeek04Application {

    public static void main(String[] args) {
        SpringApplication.run(Umc10thWeek04Application.class, args);
    }

}
