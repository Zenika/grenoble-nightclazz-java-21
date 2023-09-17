package com.zenika.grenoble.nightclazz.java21.repositories.http.mapper;

import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.DailyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.WeatherState;
import com.zenika.grenoble.nightclazz.java21.repositories.http.dto.HourlyWeather7Timer;
import com.zenika.grenoble.nightclazz.java21.repositories.http.dto.DailyWeather7Timer;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Weather7TimerMapper {

    public static List<DailyWeather> toDomain(DailyWeather7Timer dailyWeather7Timer) {
        return dailyWeather7Timer.getDataseries().stream()
                .map((@NonNull var dataSeries) -> new DailyWeather(
                        LocalDate.parse(dataSeries.getDate(), DateTimeFormatter.ofPattern("yyyyMMdd")),
                        getWeatherState(dataSeries.getWeather()),
                        dataSeries.getTemp2m().getMax(),
                        dataSeries.getTemp2m().getMin()))
                .collect(Collectors.toList());
    }

    public static List<HourlyWeather> toDomain(HourlyWeather7Timer dailyWeather7Timer) {
        var current = LocalDateTime.now();
        return dailyWeather7Timer.getDataseries().stream()
                .map(dataSeries -> new HourlyWeather(
                        current.plusHours(dataSeries.getTimepoint()),
                        getWeatherState(dataSeries.getWeather()),
                        dataSeries.getTemp2m()))
                .collect(Collectors.toList());
    }

    public static WeatherState getWeatherState(String weather) {
        switch (weather) {
            case "clear":
            case "clearday":
            case "clearnight":
                return WeatherState.SUNNY;
            case "pcloudy":
            case "pcloudyday":
            case "pcloudynight":
            case "windy":
            case "windyday":
            case "windynight":
            case "humid":
                return WeatherState.PARTLY_CLOUDY;
            case "mcloudy":
            case "mcloudynight":
            case "mcloudyday":
                return WeatherState.CLOUDY_S_SUNNY;
            case "cloudy":
            case "cloudyday":
            case "cloudynight":
                return WeatherState.CLOUDY;
            case "fog":
            case "fogday":
            case "fognight":
            case "humidnight":
            case "humidday":
                return WeatherState.FOG;
            case "lightrain":
            case "lightrainday":
            case "lightrainnight":
            case "rain":
            case "rainday":
            case "rainnight":
                return WeatherState.RAIN;
            case "oshower":
            case "oshowerday":
            case "oshowernight":
                return WeatherState.RAIN_S_SUNNY;
            case "ishower":
            case "ishowerday":
            case "ishowernight":
                return WeatherState.SUNNY_S_RAIN;
            case "lightsnow":
            case "lightsnowday":
            case "lightsnownight":
                return WeatherState.SNOW_LIGHT;
            case "snow":
            case "snowday":
            case "snownight":
                return WeatherState.SNOW;
            case "rainsnow":
            case "rainsnowday":
            case "rainsnownight":
                return WeatherState.SLEET;
            case "ts":
            case "tstorm":
            case "tsrrain":
            case "tsnight":
            case "tsday":
                return WeatherState.THUNDERSTORMS;
            default:
                System.out.println(weather + " is unknown");
                return WeatherState.UNKNOWN;
        }
    }
}
