package com.zm.a1.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @JsonProperty("name")
    private String name;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("iso2")
    private String iso2;

    @JsonProperty("population")
    private double population;

    @JsonProperty("pop_growth")
    private double populationGrowth;

    @JsonProperty("currency")
    private Currency currency;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Currency {
        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;
    }
}
