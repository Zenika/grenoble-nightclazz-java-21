package com.zenika.grenoble.nightclazz.java21.repositories.http.dto;

import java.util.List;

public record HourlyWeather7Timer(
        List<DataSeries> dataseries
) {

    public record DataSeries(
            Integer timepoint,
            Double temp2m,
            String weather,
            Wind10m wind10m
    ) {}

    public record Wind10m(
            String direction,
            Double speed
    ) {}
}
