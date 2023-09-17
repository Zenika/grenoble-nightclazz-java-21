package com.zenika.grenoble.nightclazz.java21.controllers;

import com.zenika.grenoble.nightclazz.java21.controllers.dto.CityWithWeather;
import com.zenika.grenoble.nightclazz.java21.entities.DailyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;
import com.zenika.grenoble.nightclazz.java21.repositories.CityRepository;
import com.zenika.grenoble.nightclazz.java21.services.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class WeatherController {

    private final WeatherService weatherService;
    private final CityRepository citiesRepository;

    public WeatherController(WeatherService weatherService, CityRepository citiesRepository) {
        this.weatherService = weatherService;
        this.citiesRepository = citiesRepository;
    }

    @GetMapping("/{name}/weather/daily")
    public ResponseEntity<List<DailyWeather>> getDailyWeather(@PathVariable String name) {
        var dailyWeather = weatherService.getDailyWeatherForCity(name);
        if (dailyWeather != null) {
            return ResponseEntity.ok(dailyWeather);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{name}/weather/hourly")
    public ResponseEntity<List<HourlyWeather>> getHourlyWeather(@PathVariable String name) {
        var hourlyWeather = weatherService.getHourlyWeatherForCity(name);
        if (hourlyWeather != null) {
            return ResponseEntity.ok(hourlyWeather);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/weather/hourly")
    public List<CityWithWeather> getCitiesWeather() {
        return citiesRepository.findAll().parallelStream()
                .map(city -> {
                    var weathers = weatherService.getHourlyWeatherForCity(city.getName());
                    if (weathers != null && !weathers.isEmpty()) {
                        return new CityWithWeather(city.getName(), weathers.get(0));
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
