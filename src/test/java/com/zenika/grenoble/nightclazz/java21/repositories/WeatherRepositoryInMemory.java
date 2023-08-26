package com.zenika.grenoble.nightclazz.java21.repositories;

import com.zenika.grenoble.nightclazz.java21.entities.City;
import com.zenika.grenoble.nightclazz.java21.entities.DailyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.WeatherState;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class WeatherRepositoryInMemory implements WeatherRepository {

    @Override
    public List<DailyWeather> getDailyWeather(City city) {
        return Arrays.asList(new DailyWeather(LocalDate.of(2021, 2, 23), WeatherState.CLOUDY, 28.0, 27.0));
    }

    @Override
    public List<HourlyWeather> getHourlyWeather(City city) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
