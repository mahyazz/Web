package com.zm.client.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("wind_degrees")
    private int windDegrees;

    @JsonProperty("temp")
    private double temperature;

    @JsonProperty("humidity")
    private int humidity;
}
