package com.zm.a1.controller;

import com.zm.a1.service.CountryService;
import com.zm.a1.model.Country;
import com.zm.a1.model.Weather;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final CountryService countryService;

    public ApiController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<Map<String, Object>> getAllCountries() {
        Map<String, Object> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/countries/{countryName}")
    public ResponseEntity<Country> getCountryDetails(@PathVariable String countryName) {
        Country country = new Country();
        try {
            country = countryService.getCountryData(countryName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(country);
    }

    @GetMapping("/countries/{countryName}/weather")
    public ResponseEntity<Weather> getWeatherDetails(@PathVariable String countryName) {
        Weather weather = new Weather();
        try {
            weather = countryService.getWeatherData(countryName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(weather);
    }
}
