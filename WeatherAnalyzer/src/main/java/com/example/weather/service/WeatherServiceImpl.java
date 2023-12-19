package com.example.weather.service;

import com.example.weather.model.WeatherData;
import com.example.weather.model.WeatherRequest;
import com.example.weather.repository.api.IWeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WeatherServiceImpl {

    private final IWeatherRepository weatherRepository;

    private static final double DEFAULT_AVERAGE_TEMPERATURE = 0.0;

    public WeatherData getCurrentWeather() {
        return weatherRepository.findFirstByOrderByDateDesc();
    }

    public double getAverageTemperature(WeatherRequest weatherRequest) {
        List<WeatherData> weatherDataList = weatherRepository.findAllByDateBetween(
                weatherRequest.getFrom(),
                weatherRequest.getTo()
        );

        if (weatherDataList.isEmpty()) {
            return DEFAULT_AVERAGE_TEMPERATURE;
        }

        return weatherDataList.stream()
                .mapToDouble(WeatherData::getTemperature)
                .average()
                .orElse(DEFAULT_AVERAGE_TEMPERATURE);
    }

    public List<WeatherData> getWeatherByDateRange(WeatherRequest weatherRequest) {
        return weatherRepository.findAllByDateBetween(
                weatherRequest.getFrom(),
                weatherRequest.getTo()
        );
    }

    public WeatherData saveWeatherData(WeatherData weatherData) {
        return weatherRepository.saveAndFlush(weatherData);
    }
}
