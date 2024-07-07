package com.zm.client.controller;

import com.zm.client.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.*;
import com.zm.client.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class ClientController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final HttpHeaders headers = new HttpHeaders();
    private static final String COUNTRY_API_URL = "http://localhost:8082/countries/";
    private static final String COUNTRIES_LIST_API_URL = "http://localhost:8082/countries";
    private static final String WEATHER_API_URL = "http://localhost:8082/countries/%s/weather";
    private String apiKey;

    public ClientController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    // @GetMapping
    // public String getAllCountries() {
    // headers.set("accept", "application/json");
    // headers.set("X-Api-Key", apiKey);
    // HttpEntity<String> entity = new HttpEntity<>(headers);
    // ResponseEntity<String> response =
    // restTemplate.exchange(COUNTRIES_LIST_API_URL, HttpMethod.GET,
    // entity, String.class);
    // String responseBody = response.getBody();
    // Map<String, Object> countries = objectMapper.readValue(responseBody, new
    // TypeReference<Map<String, Object>>() {
    // });

    // List<String> res = new ArrayList<String>();
    // List<Map<String, String>> list = countries.get("countries");
    // for (Map<String, String> country : list) {
    // res.add(country.get("name"));
    // }
    // String json = "";
    // try {
    // json = objectMapper.writeValueAsString(countries);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return json;
    // }

    // @GetMapping("/{name}")
    // public Country getCountryByName(@PathVariable String name) throws IOException
    // {
    // return countryService.getCountryData(name);
    // }

    // @GetMapping("/{name}/weather")
    // public Weather getWeatherByName(@PathVariable String name) throws IOException
    // {
    // return countryService.getWeatherData(name);
    // }
}
