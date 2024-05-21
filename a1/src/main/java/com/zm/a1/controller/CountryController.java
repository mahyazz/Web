package com.zm.a1.controller;

import com.zm.a1.model.Country;
import com.zm.a1.model.Weather;
import com.zm.a1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public Map<String, Object> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        Map<String, Object> response = new HashMap<>();
        response.put("countries", countries);
        response.put("count", countries.size());
        return response;
    }

    @GetMapping("/{name}")
    public Country getCountryByName(@PathVariable String name) {
        return countryService.getCountryByName(name);
    }

    @GetMapping("/{name}/weather")
    public Weather getWeatherByCountryName(@PathVariable String name) {
        return countryService.getWeatherByCountryName(name);
    }
}
