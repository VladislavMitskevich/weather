package com.example.weather.scheduler;

import com.example.weather.model.WeatherData;
import com.example.weather.repository.api.IWeatherRepository;
import com.example.weather.service.WeatherApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataScheduler {

    private final Logger logger = LoggerFactory.getLogger(WeatherDataScheduler.class);

    private final WeatherApiService weatherApiService;
    private final IWeatherRepository weatherRepository;

    @Value("${weatherapi.city}")
    private String city;

    @Autowired
    public WeatherDataScheduler(WeatherApiService weatherApiService, IWeatherRepository weatherRepository) {
        this.weatherApiService = weatherApiService;
        this.weatherRepository = weatherRepository;
    }

    @Scheduled(cron = "0 */5 * * * *") // Запуск каждые 5 минут
    public void saveWeatherData() {
        try {
            WeatherData weatherData = weatherApiService.getCurrentWeather(city);
            weatherRepository.saveAndFlush(weatherData);
        } catch (Exception e) {
            logger.error("Произошла ошибка при сохранении данных о погоде: {}", e.getMessage());
        }
    }
}
