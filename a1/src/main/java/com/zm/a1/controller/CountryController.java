package com.zm.a1.controller;

import com.zm.a1.service.CountryService;
import com.zm.a1.model.Country;
import com.zm.a1.model.Weather;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/countries")
public class CountryController {
    // private static final String WEATHER_API_URL =
    // "https://api-ninjas.com/api/weather?city=";

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public Map<String, Object> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{name}")
    public Country getCountryByName(@PathVariable String name) throws IOException {
        return countryService.getCountryData(name);
    }

    // @GetMapping("/{name}/weather")
    // public Weather getWeatherByCountryName(@PathVariable String name) {
    // Country country = getCountryByName(name);
    // String url = WEATHER_API_URL + country.getCapital();
    // headers.set("X-Api-Key", apiProperties.getApiKey());
    // HttpEntity<String> entity = new HttpEntity<>(headers);
    // ResponseEntity<Weather> response = restTemplate.exchange(url, HttpMethod.GET,
    // entity, Weather.class);
    // return response.getBody();
    // }
}
