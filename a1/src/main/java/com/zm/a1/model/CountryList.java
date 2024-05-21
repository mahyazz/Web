package com.zm.a1.model;

import lombok.Data;
import java.util.List;

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

    public List<CountryData> getData() {
        return data;
    }
}
