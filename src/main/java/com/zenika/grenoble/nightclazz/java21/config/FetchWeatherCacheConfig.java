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
        var cities = citiesRepository.findAll();
        var executor = Executors.newFixedThreadPool(cities.size()); // You can adjust the thread pool size as needed
        for (var city : cities) {
            executor.submit(() -> {
                var jobDaily = executor.submit(() -> weatherService.getDailyWeatherForCity(city.getName()));
                var jobHourly = executor.submit(() -> weatherService.getHourlyWeatherForCity(city.getName()));
                try {
                    jobDaily.get();
                    jobHourly.get();
                } catch (Exception e) {
                    System.out.println("Fetch " + city.getName() + " weather");
                    throw new RuntimeException(e);
                }
            });
        }
        executor.shutdown();
    }
}
