package com.zm.a1.controller;

import com.zm.a1.model.Country;
import com.zm.a1.model.Weather;
import com.zm.a1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
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
