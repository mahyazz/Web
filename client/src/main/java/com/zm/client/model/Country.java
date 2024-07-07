package com.zm.client.model;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
