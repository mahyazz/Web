package com.zm.a1;

import com.zm.a1.config.ApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperties.class)
public class A1Application {

	public static void main(String[] args) {
		SpringApplication.run(A1Application.class, args);
	}

}
