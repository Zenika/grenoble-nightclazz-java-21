package com.zenika.grenoble.nightclazz.java21.config;

import com.zenika.grenoble.nightclazz.java21.entities.City;
import com.zenika.grenoble.nightclazz.java21.repositories.CityRepository;
import com.zenika.grenoble.nightclazz.java21.services.WeatherService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class FetchWeatherCacheConfig implements ApplicationRunner {

    private final CityRepository citiesRepository;
    private final WeatherService weatherService;

    public FetchWeatherCacheConfig(CityRepository citiesRepository, WeatherService weatherService) {
        this.citiesRepository = citiesRepository;
        this.weatherService = weatherService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<City> cities = citiesRepository.findAll();
        for (City city : cities) {
            Thread.ofVirtual().name(city.getName()).start(() -> {
                Thread.ofVirtual().name("DailyWeather").start(() -> weatherService.getDailyWeatherForCity(city.getName()));
                Thread.ofVirtual().name("HourlyWeather").start(() -> weatherService.getHourlyWeatherForCity(city.getName()));
            });
        }
    }
}
