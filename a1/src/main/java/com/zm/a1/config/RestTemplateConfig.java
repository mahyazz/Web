package com.zm.a1.config;

import com.zm.a1.controller.CountryController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CountryController countryController(RestTemplate restTemplate) {
        return new CountryController(restTemplate);
    }
}
