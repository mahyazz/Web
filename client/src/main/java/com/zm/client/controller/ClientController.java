package com.zm.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class ClientController {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();
    private static final String COUNTRY_API_URL = "http://localhost:8082/api/countries/";
    private static final String COUNTRIES_LIST_API_URL = "http://localhost:8082/countries";
    private static final String WEATHER_API_URL = "http://localhost:8082/countries/%s/weather";

    @Value("${api-key}")
    private String apiKey;

    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCountries() {
        headers.set("accept", "application/json");
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(COUNTRIES_LIST_API_URL, HttpMethod.GET, entity,
                Object.class);
    }

    @GetMapping("/countries/{name}")
    public ResponseEntity<Object> getCountryByName(@PathVariable String name) throws IOException {
        headers.set("accept", "application/json");
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(COUNTRY_API_URL, HttpMethod.GET, entity,
                Object.class);
    }

    @GetMapping("/{name}/weather")
    public ResponseEntity<Object> getWeatherByName(@PathVariable String name) throws IOException {
        headers.set("accept", "application/json");
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(WEATHER_API_URL, HttpMethod.GET, entity,
                Object.class);
    }
}
