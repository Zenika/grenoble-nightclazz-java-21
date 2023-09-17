package com.zenika.grenoble.nightclazz.java21.controllers.dto;

import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;

public record CityWithWeather(
        String name,
        HourlyWeather weather
) {}
