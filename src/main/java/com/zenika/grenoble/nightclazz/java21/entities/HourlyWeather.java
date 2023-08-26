package com.zenika.grenoble.nightclazz.java21.entities;

import java.time.LocalDateTime;

public class HourlyWeather {

    private LocalDateTime date;
    private WeatherState weather;
    private Double temperature;

    public HourlyWeather() {
    }

    public HourlyWeather(LocalDateTime date, WeatherState weather, Double temperature) {
        this.date = date;
        this.weather = weather;
        this.temperature = temperature;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public WeatherState getWeather() {
        return weather;
    }

    public void setWeather(WeatherState weather) {
        this.weather = weather;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
