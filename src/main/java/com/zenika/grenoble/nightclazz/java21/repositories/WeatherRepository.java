package com.zenika.grenoble.nightclazz.java21.repositories;

import com.zenika.grenoble.nightclazz.java21.entities.City;
import com.zenika.grenoble.nightclazz.java21.entities.DailyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;

import java.util.List;

public interface WeatherRepository {
    List<HourlyWeather> getHourlyWeather(City city);

    List<DailyWeather> getDailyWeather(City city);
}
