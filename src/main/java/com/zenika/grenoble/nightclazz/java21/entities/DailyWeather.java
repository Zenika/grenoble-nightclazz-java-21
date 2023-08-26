package com.zenika.grenoble.nightclazz.java21.entities;

import java.time.LocalDate;

public class DailyWeather {

    private LocalDate day;
    private WeatherState weather;
    private Double temperatureMax;
    private Double temperatureMin;

    public DailyWeather() {}

    public DailyWeather(LocalDate day, WeatherState weather, Double temperatureMax, Double temperatureMin) {
        this.day = day;
        this.weather = weather;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public WeatherState getWeather() {
        return weather;
    }

    public void setWeather(WeatherState weather) {
        this.weather = weather;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }
}

