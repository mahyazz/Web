package com.zm.a1.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Weather {
    private String country_name;
    private String capital;
    private double wind_speed;
    private int wind_degrees;
    private double temp;
    private int humidity;
}
