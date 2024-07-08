package com.zm.a1.controller;

import com.zm.a1.service.CountryService;
import com.zm.a1.service.AuthenticationService;
import com.zm.a1.model.Country;
import com.zm.a1.model.Weather;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final CountryService countryService;
    private final AuthenticationService authService;

    public ApiController(CountryService countryService, AuthenticationService authService) {
        this.countryService = countryService;
        this.authService = authService;
    }

    @GetMapping("/countries")
    public ResponseEntity<Object> getAllCountries(@RequestHeader(value = "X-Api-Key") String apiKey) {
        if (authService.authenticateApiKey(apiKey)) {
            Map<String, Object> countries = countryService.getAllCountries();
            return ResponseEntity.ok(countries);
        } else {
            return ResponseEntity.status(403).body("Forbidden: Invalid API Key");
        }
    }

    @GetMapping("/countries/{countryName}")
    public ResponseEntity<Object> getCountryDetails(@PathVariable String countryName,
            @RequestHeader(value = "X-Api-Key") String apiKey) {
        if (authService.authenticateApiKey(apiKey)) {
            Country country = new Country();
            try {
                country = countryService.getCountryData(countryName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.status(403).body("Forbidden: Invalid API Key");
        }
    }

    @GetMapping("/countries/{countryName}/weather")
    public ResponseEntity<Object> getWeatherDetails(@PathVariable String countryName,
            @RequestHeader(value = "X-Api-Key") String apiKey) {
        if (authService.authenticateApiKey(apiKey)) {
            Weather weather = new Weather();
            try {
                weather = countryService.getWeatherData(countryName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(weather);
        } else {
            return ResponseEntity.status(403).body("Forbidden: Invalid API Key");
        }
    }
}
