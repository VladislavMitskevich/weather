package com.example.weather.mapper;

import com.example.weather.dto.WeatherDataDTO;
import com.example.weather.model.WeatherCondition;
import com.example.weather.model.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherDataMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", dateFormat = "dd-MM-yyyy HH:mm:ssSSS")
    WeatherDataDTO toWeatherDataDTO(WeatherData weatherData);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", dateFormat = "dd-MM-yyyy HH:mm:ssSSS")
    WeatherData toWeatherData(WeatherDataDTO weatherDataDTO);

    default String mapWeatherCondition(WeatherCondition condition) {
        return condition.name();
    }

    default WeatherCondition mapWeatherCondition(String condition) {
        return WeatherCondition.valueOf(condition);
    }
}

