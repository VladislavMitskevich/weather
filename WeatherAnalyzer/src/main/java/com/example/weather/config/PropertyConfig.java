package com.example.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weatherapi")
@Data
public class PropertyConfig {
    private String url;
    private String apiKey;
    private String city;
}


