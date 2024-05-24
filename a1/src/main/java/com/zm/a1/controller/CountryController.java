package com.zm.a1.controller;

import com.zm.a1.model.Country;
import com.zm.a1.model.Weather;
import com.zm.a1.model.CountryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import com.fasterxml.jackson.databind.*;
import com.zm.a1.config.ApiProperties;

import java.util.*;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    private static final String COUNTRY_API_URL = "https://api-ninjas.com/api/country?name=";
    private static final String WEATHER_API_URL = "https://api-ninjas.com/api/weather?city=";
    private static final String COUNTRIES_LIST_API_URL = "https://countriesnow.space/api/v0.1/countries";

    public CountryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Autowired
    private ApiProperties apiProperties;

    @GetMapping
    public Map<String, Object> getAllCountries() {
        CountryList httpResponse = restTemplate.getForObject(COUNTRIES_LIST_API_URL, CountryList.class);
        List<Map<String, String>> countries = new ArrayList<Map<String, String>>();
        if (httpResponse != null && !httpResponse.isError()) {
            for (CountryList.CountryData data : httpResponse.getData()) {
                Map<String, String> country = new HashMap<>();
                country.put("name", data.getCountry());
                countries.add(country);
            }
        } else {
            countries = List.of();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("count", countries.size());
        response.put("countries", countries);
        return response;
    }

    @GetMapping("/{name}")
    public Country getCountryByName(@PathVariable String name) {
        String url = COUNTRY_API_URL + name;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiProperties.getApiKey());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Country> response = restTemplate.exchange(url, HttpMethod.GET, entity, Country.class);
        return response.getBody();
    }

    @GetMapping("/{name}/weather")
    public Weather getWeatherByCountryName(@PathVariable String name) {
        Country country = getCountryByName(name);
        String url = WEATHER_API_URL + country.getCapital();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiProperties.getApiKey());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Weather> response = restTemplate.exchange(url, HttpMethod.GET, entity, Weather.class);
        return response.getBody();
    }
}
