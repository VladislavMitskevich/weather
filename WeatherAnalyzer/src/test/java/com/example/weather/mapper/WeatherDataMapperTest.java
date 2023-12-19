package com.example.weather.mapper;

import com.example.weather.dto.WeatherDataDTO;
import com.example.weather.model.WeatherCondition;
import com.example.weather.model.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataMapperTest {

    @InjectMocks
    private WeatherDataMapper weatherDataMapper;

    @Test
    public void testToWeatherDataDTO() {
        WeatherData weatherData = new WeatherData();
        weatherData.setId(1L);
        weatherData.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        weatherData.setTemperature(25.0);
        weatherData.setWindSpeed(10.0);
        weatherData.setPressure(1013.25);
        weatherData.setHumidity(50);
        weatherData.setCondition(WeatherCondition.SUNNY);
        weatherData.setLocation("Minsk");

        WeatherDataDTO expectedWeatherDataDTO = new WeatherDataDTO();
        expectedWeatherDataDTO.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        expectedWeatherDataDTO.setTemperature(25.0);
        expectedWeatherDataDTO.setWindSpeed(10.0);
        expectedWeatherDataDTO.setPressure(1013.25);
        expectedWeatherDataDTO.setHumidity(50);
        expectedWeatherDataDTO.setCondition("SUNNY");
        expectedWeatherDataDTO.setLocation("Minsk");

        WeatherDataDTO actualWeatherDataDTO = weatherDataMapper.toWeatherDataDTO(weatherData);

        assertEquals(expectedWeatherDataDTO, actualWeatherDataDTO);
    }

    @Test
    public void testToWeatherData() {
        WeatherDataDTO weatherDataDTO = new WeatherDataDTO();
        weatherDataDTO.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        weatherDataDTO.setTemperature(25.0);
        weatherDataDTO.setWindSpeed(10.0);
        weatherDataDTO.setPressure(1013.25);
        weatherDataDTO.setHumidity(50);
        weatherDataDTO.setCondition("SUNNY");
        weatherDataDTO.setLocation("Minsk");

        WeatherData expectedWeatherData = new WeatherData();
        expectedWeatherData.setDate(LocalDateTime.of(2021, 8, 1, 12, 0));
        expectedWeatherData.setTemperature(25.0);
        expectedWeatherData.setWindSpeed(10.0);
        expectedWeatherData.setPressure(1013.25);
        expectedWeatherData.setHumidity(50);
        expectedWeatherData.setCondition(WeatherCondition.SUNNY);
        expectedWeatherData.setLocation("Minsk");

        WeatherData actualWeatherData = weatherDataMapper.toWeatherData(weatherDataDTO);

        assertEquals(expectedWeatherData, actualWeatherData);
    }
}

