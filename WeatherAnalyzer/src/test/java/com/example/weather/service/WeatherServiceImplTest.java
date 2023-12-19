package com.example.weather.service;

import com.example.weather.model.WeatherData;
import com.example.weather.model.WeatherRequest;
import com.example.weather.repository.api.IWeatherRepository;
import com.example.weather.model.WeatherCondition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @Mock
    private IWeatherRepository weatherRepository;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    public void testGetCurrentWeather() {
        WeatherData expectedWeatherData = new WeatherData();
        expectedWeatherData.setId(1L);
        expectedWeatherData.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        expectedWeatherData.setTemperature(25.0);
        expectedWeatherData.setWindSpeed(10.0);
        expectedWeatherData.setPressure(1013.25);
        expectedWeatherData.setHumidity(50);
        expectedWeatherData.setCondition(WeatherCondition.CLEAR);
        expectedWeatherData.setLocation("Minsk");

        Mockito.when(weatherRepository.findFirstByOrderByDateDesc()).thenReturn(expectedWeatherData);

        WeatherData actualWeatherData = weatherService.getCurrentWeather();

        assertEquals(expectedWeatherData, actualWeatherData);
    }

    @Test
    public void testGetAverageTemperature() {
        LocalDateTime from = LocalDateTime.of(2021, 7, 1, 0, 0);
        LocalDateTime to = LocalDateTime.of(2021, 7, 31, 23, 59, 59);

        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setFrom(from);
        weatherRequest.setTo(to);

        WeatherData weatherData1 = new WeatherData();
        weatherData1.setTemperature(20.5);

        WeatherData weatherData2 = new WeatherData();
        weatherData2.setTemperature(23.8);

        List<WeatherData> weatherDataList = Arrays.asList(weatherData1, weatherData2);

        Mockito.when(weatherRepository.findAllByDateBetween(weatherRequest.getFrom(), weatherRequest.getTo())).thenReturn(weatherDataList);

        double expectedAverageTemperature = 22.15;
        double actualAverageTemperature = weatherService.getAverageTemperature(weatherRequest);

        assertEquals(expectedAverageTemperature, actualAverageTemperature, 0.01);
    }

    @Test
    public void testGetWeatherByDateRange() {
        LocalDateTime from = LocalDateTime.of(2021, 7, 1, 0, 0);
        LocalDateTime to = LocalDateTime.of(2021, 7, 31, 23, 59, 59);

        WeatherRequest weatherRequest = new WeatherRequest();
        weatherRequest.setFrom(from);
        weatherRequest.setTo(to);

        WeatherData weatherData1 = new WeatherData();
        weatherData1.setId(1L);
        weatherData1.setDate(LocalDateTime.of(2021, 7, 15, 12, 0));
        weatherData1.setTemperature(20.5);
        weatherData1.setWindSpeed(10.0);
        weatherData1.setPressure(1013.25);
        weatherData1.setHumidity(50);
        weatherData1.setCondition(WeatherCondition.SUNNY);
        weatherData1.setLocation("Minsk");

        WeatherData weatherData2 = new WeatherData();
        weatherData2.setId(2L);
        weatherData2.setDate(LocalDateTime.of(2021, 7, 20, 15, 0));
        weatherData2.setTemperature(23.8);
        weatherData2.setWindSpeed(7.5);
        weatherData2.setPressure(1010.5);
        weatherData2.setHumidity(60);
        weatherData2.setCondition(WeatherCondition.PARTLY_CLOUDY);
        weatherData2.setLocation("Minsk");

        List<WeatherData> expectedWeatherDataList = Arrays.asList(weatherData1, weatherData2);

        Mockito.when(weatherRepository.findAllByDateBetween(weatherRequest.getFrom(), weatherRequest.getTo())).thenReturn(expectedWeatherDataList);

        List<WeatherData> actualWeatherDataList = weatherService.getWeatherByDateRange(weatherRequest);

        assertEquals(expectedWeatherDataList.size(), actualWeatherDataList.size());
        assertEquals(expectedWeatherDataList.get(0), actualWeatherDataList.get(0));
        assertEquals(expectedWeatherDataList.get(1), actualWeatherDataList.get(1));
    }
}