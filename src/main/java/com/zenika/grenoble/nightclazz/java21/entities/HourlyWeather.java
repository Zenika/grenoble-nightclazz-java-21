package com.zenika.grenoble.nightclazz.java21.entities;

import java.time.LocalDateTime;

public record HourlyWeather(
        LocalDateTime date,
        WeatherState weather,
        Double temperature
) {}
