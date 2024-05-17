package com.zm.a1.service;

import com.zm.a1.model.Country;
import com.zm.a1.model.Weather;
import com.zm.a1.config.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {

    private static final String COUNTRY_API_URL = "https://api-ninjas.com/api/country?name=";
    private static final String WEATHER_API_URL = "https://api-ninjas.com/api/weather?city=";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiProperties apiProperties;

    public List<Country> getAllCountries() {
        return Arrays.asList(
                new Country("Iran"),
                new Country("Japan"),
                new Country("Norway"),
                new Country("Peru"),
                new Country("Germany"));
    }

    public Country getCountryByName(String name) {
        String url = COUNTRY_API_URL + name;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiProperties.getApiKey());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Country> response = restTemplate.exchange(url, HttpMethod.GET, entity, Country.class);
        return response.getBody();
    }

    public Weather getWeatherByCountryName(String name) {
        Country country = getCountryByName(name);
        String url = WEATHER_API_URL + country.getCapital();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiProperties.getApiKey());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Weather> response = restTemplate.exchange(url, HttpMethod.GET, entity, Weather.class);
        return response.getBody();
    }
}
