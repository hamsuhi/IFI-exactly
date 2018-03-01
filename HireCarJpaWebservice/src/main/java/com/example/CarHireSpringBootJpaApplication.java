package com.example;
/*
 * Mô hình bài:
 * Chay từ: Main-> controller -> service -> repository -> bean
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//@EnableJpaAuditing
@SpringBootApplication
public class CarHireSpringBootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarHireSpringBootJpaApplication.class, args);
	}
}
