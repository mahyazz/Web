package com.zm.a1.service;

import com.zm.a1.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.zm.a1.model.CountryList;
import java.util.*;

@Service
public class CountryService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final HttpHeaders headers = new HttpHeaders();
    private static final String COUNTRY_API_URL = "https://api-ninjas.com/api/country?name=";
    private static final String COUNTRIES_LIST_API_URL = "https://countriesnow.space/api/v0.1/countries";

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

    public Country getCountryData(String name) {
        String url = COUNTRY_API_URL + name;
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String responseBody = response.getBody();

            // Log the response for debugging
            System.out.println("Response from API: " + responseBody);

            // Check if response is in JSON format
            if (response.getHeaders().getContentType() != null &&
                    response.getHeaders().getContentType().toString().contains("application/json")) {
                return objectMapper.readValue(responseBody, Country.class);
            } else {
                // Handle non-JSON response
                System.err.println("Unexpected response content type: " + response.getHeaders().getContentType());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
