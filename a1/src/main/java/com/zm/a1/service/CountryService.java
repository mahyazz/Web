package com.zm.a1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.a1.model.*;

@Service
public class CountryService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final HttpHeaders headers = new HttpHeaders();
    private static final String COUNTRY_API_URL = "https://api.api-ninjas.com/v1/country?name=";
    private static final String COUNTRIES_LIST_API_URL = "https://countriesnow.space/api/v0.1/countries";
    private static final String WEATHER_API_URL = "https://api.api-ninjas.com/v1/weather?lat=%f&lon=%f";
    private static final String GEOCODING_API_URL = "https://api.api-ninjas.com/v1/geocoding?city=%s&country=%s";

    @Value("${api.ninjas.api-key}")
    private String apiKey;

    public CountryService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

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

    public Country getCountryData(String name) throws IOException {
        String url = COUNTRY_API_URL + name;
        headers.set("accept", "application/json");
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();
        List<Country> countries = objectMapper.readValue(responseBody, new TypeReference<List<Country>>() {
        });
        return countries != null && !countries.isEmpty() ? countries.get(0) : null;
    }

    public GeoData getGeoData(String capital, String country) throws IOException {
        String url = String.format(GEOCODING_API_URL, capital, country);
        headers.set("accept", "application/json");
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();
        List<GeoData> geoData = objectMapper.readValue(responseBody, new TypeReference<List<GeoData>>() {
        });
        return geoData != null && !geoData.isEmpty() ? geoData.get(0) : null;
    }

    public Weather getWeatherData(String name) throws IOException {
        String capital = getCountryData(name).getCapital();
        GeoData geoData = getGeoData(capital, name);
        String url = String.format(WEATHER_API_URL, geoData.getLatitude(), geoData.getLongitude());
        headers.set("accept", "application/json");
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();
        Weather weather = objectMapper.readValue(responseBody, Weather.class);
        weather.setCountryName(name);
        weather.setCapital(capital);
        return weather;
    }
}
