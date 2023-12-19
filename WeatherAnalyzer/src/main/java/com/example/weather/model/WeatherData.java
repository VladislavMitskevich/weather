package com.example.weather.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@Data
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private Double temperature;
    private Double windSpeed;
    private Double pressure;
    private Integer humidity;

    @Enumerated(EnumType.STRING)
    private WeatherCondition condition;
    private String location;
}

