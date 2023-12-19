package com.example.weather.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherDataDTO {
    private LocalDateTime date;
    private Double temperature;
    private Double windSpeed;
    private Double pressure;
    private Integer humidity;
    private String condition;
    private String location;
}


