package com.zm.a1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class A1Application {
	public static void main(String[] args) {
		SpringApplication.run(A1Application.class, args);
	}
}
