package com.zenika.grenoble.nightclazz.java21.services;

import com.zenika.grenoble.nightclazz.java21.entities.City;
import com.zenika.grenoble.nightclazz.java21.entities.DailyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;
import com.zenika.grenoble.nightclazz.java21.repositories.CityRepository;
import com.zenika.grenoble.nightclazz.java21.repositories.WeatherRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;

    public WeatherService(CityRepository cityRepository, WeatherRepository weatherRepository) {
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }

    @Cacheable(cacheNames = "dailyWeather")
    public List<DailyWeather> getDailyWeatherForCity(String name) {
        City city = cityRepository.getByName(name);
        if (city != null) {
            return weatherRepository.getDailyWeather(city);
        }
        return null;
    }

    @Cacheable(cacheNames = "hourlyWeather")
    public List<HourlyWeather> getHourlyWeatherForCity(String name) {
        City city = cityRepository.getByName(name);
        if (city != null) {
            return weatherRepository.getHourlyWeather(city);
        }
        return null;
    }
}
