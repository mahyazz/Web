package com.zm.client.model;

import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CountryList {
    private boolean error;
    private String msg;
    private List<CountryData> data;

    @Data
    public static class CountryData {
        private String country;
        private List<String> cities;
    }
}
