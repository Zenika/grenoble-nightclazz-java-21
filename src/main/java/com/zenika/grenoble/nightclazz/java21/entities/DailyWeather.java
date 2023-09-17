package com.zenika.grenoble.nightclazz.java21.entities;

import java.time.LocalDate;

public record DailyWeather(
        LocalDate day,
        WeatherState weather,
        Double temperatureMax,
        Double temperatureMin
) {}
