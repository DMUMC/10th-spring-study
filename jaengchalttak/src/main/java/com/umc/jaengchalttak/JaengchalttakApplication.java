package com.umc.jaengchalttak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JaengchalttakApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaengchalttakApplication.class, args);
	}

}
