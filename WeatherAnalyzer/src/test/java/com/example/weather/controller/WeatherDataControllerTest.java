package com.example.weather.controller;

import com.example.weather.dto.WeatherDataDTO;
import com.example.weather.mapper.WeatherDataMapper;
import com.example.weather.model.WeatherCondition;
import com.example.weather.model.WeatherData;
import com.example.weather.service.WeatherServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataControllerTest {

    @Mock
    private WeatherServiceImpl weatherService;

    @Mock
    private WeatherDataMapper weatherDataMapper;

    @InjectMocks
    private WeatherDataController weatherDataController;

    @Test
    public void testGetCurrentWeather() {
        WeatherDataDTO expectedWeatherDataDTO = new WeatherDataDTO();
        expectedWeatherDataDTO.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        expectedWeatherDataDTO.setTemperature(25.0);
        expectedWeatherDataDTO.setWindSpeed(10.0);
        expectedWeatherDataDTO.setPressure(1013.25);
        expectedWeatherDataDTO.setHumidity(50);
        expectedWeatherDataDTO.setCondition("SUNNY");
        expectedWeatherDataDTO.setLocation("Minsk");

        WeatherData weatherData = new WeatherData();
        weatherData.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));

        weatherData.setTemperature(25.0);
        weatherData.setWindSpeed(10.0);
        weatherData.setPressure(1013.25);
        weatherData.setHumidity(50);
        weatherData.setCondition(WeatherCondition.SUNNY);
        weatherData.setLocation("Minsk");

        Mockito.when(weatherService.getCurrentWeather()).thenReturn(weatherData);
        Mockito.when(weatherDataMapper.toWeatherDataDTO(weatherData)).thenReturn(expectedWeatherDataDTO);

        WeatherDataDTO actualWeatherDataDTO = weatherDataController.getCurrentWeather();

        assertEquals(expectedWeatherDataDTO, actualWeatherDataDTO);
    }

    @Test
    public void testGetAverageTemperature() {
        LocalDateTime from = LocalDateTime.of(2021, 8, 1, 0, 0);
        LocalDateTime to = LocalDateTime.of(2021, 8, 31, 23, 59, 59);

        double expectedAverageTemperature = 22.15;

        Mockito.when(weatherService.getAverageTemperature(from, to)).thenReturn(expectedAverageTemperature);

        double actualAverageTemperature = weatherDataController.getAverageTemperature(from, to);

        assertEquals(expectedAverageTemperature, actualAverageTemperature, 0.01);
    }

    @Test
    public void testGetWeatherByDateRange() {
        LocalDateTime from = LocalDateTime.of(2021, 8, 1, 0, 0);
        LocalDateTime to = LocalDateTime.of(2021, 8, 31, 23, 59, 59);

        WeatherDataDTO expectedWeatherDataDTO = new WeatherDataDTO();
        expectedWeatherDataDTO.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        expectedWeatherDataDTO.setTemperature(25.0);
        expectedWeatherDataDTO.setWindSpeed(10.0);
        expectedWeatherDataDTO.setPressure(1013.25);
        expectedWeatherDataDTO.setHumidity(50);
        expectedWeatherDataDTO.setCondition("SUNNY");
        expectedWeatherDataDTO.setLocation("Minsk");

        WeatherData weatherData = new WeatherData();
        weatherData.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        weatherData.setTemperature(25.0);
        weatherData.setWindSpeed(10.0);
        weatherData.setPressure(1013.25);
        weatherData.setHumidity(50);
        weatherData.setCondition(WeatherCondition.SUNNY);
        weatherData.setLocation("Minsk");

        List<WeatherData> weatherDataList = Arrays.asList(weatherData);
        List<WeatherDataDTO> expectedWeatherDataDTOList = Arrays.asList(expectedWeatherDataDTO);

        Mockito.when(weatherService.getWeatherByDateRange(from, to)).thenReturn(weatherDataList);
        Mockito.when(weatherDataMapper.toWeatherDataDTO(weatherData)).thenReturn(expectedWeatherDataDTO);

        List<WeatherDataDTO> actualWeatherDataDTOList = weatherDataController.getWeatherByDateRange(from, to);

        assertEquals(expectedWeatherDataDTOList.size(), actualWeatherDataDTOList.size());
        assertEquals(expectedWeatherDataDTOList.get(0), actualWeatherDataDTOList.get(0));
    }
}

