package com.zenika.grenoble.nightclazz.java21.repositories.http.dto;

import java.util.List;

public record DailyWeather7Timer(
    List<DataSeries> dataseries
) {

    public record DataSeries(
            String date,
            Temp2m temp2m,
            String weather,
            Double wind10m_max
    ) {}

    public record Temp2m(
            Double max,
            Double min
    ) {}
}
