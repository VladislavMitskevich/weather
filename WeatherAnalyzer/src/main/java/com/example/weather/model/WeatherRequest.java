package com.example.weather.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherRequest {
    private LocalDateTime from;
    private LocalDateTime to;
}

