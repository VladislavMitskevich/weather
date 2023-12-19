package com.example.weather.controller;

import com.example.weather.dto.WeatherDataDTO;
import com.example.weather.mapper.WeatherDataMapper;
import com.example.weather.model.WeatherData;
import com.example.weather.model.WeatherRequest;
import com.example.weather.service.WeatherServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherDataController {

    private final WeatherServiceImpl weatherService;
    private final WeatherDataMapper weatherDataMapper;

    @Autowired
    public WeatherDataController(WeatherServiceImpl weatherService, WeatherDataMapper weatherDataMapper) {
        this.weatherService = weatherService;
        this.weatherDataMapper = weatherDataMapper;
    }

    @GetMapping("/current")
    public WeatherDataDTO getCurrentWeather() {
        WeatherData weatherData = weatherService.getCurrentWeather();
        return weatherDataMapper.toWeatherDataDTO(weatherData);
    }

    @GetMapping("/average")
    public double getAverageTemperature(@RequestParam("from") LocalDateTime from,
                                        @RequestParam("to") LocalDateTime to) {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setFrom(from);
        weatherRequest.setTo(to);
        return weatherService.getAverageTemperature(weatherRequest);
    }

    @GetMapping("/range")
    public List<WeatherDataDTO> getWeatherByDateRange(@RequestParam("from") LocalDateTime from,
                                                      @RequestParam("to") LocalDateTime to) {
        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setFrom(from);
        weatherRequest.setTo(to);
        List<WeatherData> weatherDataList = weatherService.getWeatherByDateRange(weatherRequest);
        return weatherDataList.stream()
                .map(weatherDataMapper::toWeatherDataDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public WeatherDataDTO saveWeatherData(@RequestBody WeatherDataDTO weatherDataDTO) {
        WeatherData weatherData = weatherDataMapper.toWeatherData(weatherDataDTO);
        WeatherData savedWeatherData = weatherService.saveWeatherData(weatherData);
        return weatherDataMapper.toWeatherDataDTO(savedWeatherData);
    }
}
