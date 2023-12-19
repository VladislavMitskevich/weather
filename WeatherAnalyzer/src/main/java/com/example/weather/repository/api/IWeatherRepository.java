package com.example.weather.repository.api;

import com.example.weather.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IWeatherRepository extends JpaRepository<WeatherData, Long> {
    WeatherData findFirstByOrderByDateDesc();
    List<WeatherData> findAllByLocationOrderByDate(String location);
    List<WeatherData> findAllByDateBetween(LocalDateTime from, LocalDateTime to);
}





