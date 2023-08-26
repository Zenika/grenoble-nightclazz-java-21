package com.zenika.grenoble.nightclazz.java21.controllers.dto;

import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;

public class CityWithWeather {
    private final String name;
    private final HourlyWeather weather;

    public CityWithWeather(String name, HourlyWeather weather) {
        this.name = name;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public HourlyWeather getWeather() {
        return weather;
    }
}
