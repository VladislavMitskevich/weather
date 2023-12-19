package com.example.weather.service;

import com.example.weather.config.PropertyConfig;
import com.example.weather.model.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@Service
public class WeatherApiService {

    private final PropertyConfig propertyConfig;

    private final Logger logger = LoggerFactory.getLogger(WeatherApiService.class);

    @Autowired
    public WeatherApiService(PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }

    public WeatherData getCurrentWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(propertyConfig.getUrl())
                .queryParam("q", city)
                .queryParam("key", propertyConfig.getApiKey());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<WeatherData> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    WeatherData.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new WeatherApiException("Произошла ошибка при получении данных о погоде");
            }
        } catch (RestClientException e) {
            logger.error("Произошла ошибка при получении данных о погоде: {}", e.getMessage());
            throw new WeatherApiException("Произошла ошибка при получении данных о погоде", e);
        }
    }

    public static class WeatherApiException extends RuntimeException {

        public WeatherApiException(String message) {
            super(message);
        }

        public WeatherApiException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
