package com.zenika.grenoble.nightclazz.java21.repositories.http.mapper;

import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.DailyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.WeatherState;
import com.zenika.grenoble.nightclazz.java21.repositories.http.dto.HourlyWeather7Timer;
import com.zenika.grenoble.nightclazz.java21.repositories.http.dto.DailyWeather7Timer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Weather7TimerMapper {

    public static List<DailyWeather> toDomain(DailyWeather7Timer dailyWeather7Timer) {
        return dailyWeather7Timer.getDataseries().stream()
                .map(dataSeries -> new DailyWeather(
                        LocalDate.parse(dataSeries.getDate(), DateTimeFormatter.ofPattern("yyyyMMdd")),
                        getWeatherState(dataSeries.getWeather()),
                        dataSeries.getTemp2m().getMax(),
                        dataSeries.getTemp2m().getMin()))
                .collect(Collectors.toList());
    }

    public static List<HourlyWeather> toDomain(HourlyWeather7Timer dailyWeather7Timer) {
        LocalDateTime current = LocalDateTime.now();
        return dailyWeather7Timer.getDataseries().stream()
                .map(dataSeries -> new HourlyWeather(
                        current.plusHours(dataSeries.getTimepoint()),
                        getWeatherState(dataSeries.getWeather()),
                        dataSeries.getTemp2m()))
                .collect(Collectors.toList());
    }

    public static WeatherState getWeatherState(String weather) {
        return switch (weather) {
            case "clear", "clearday", "clearnight" -> WeatherState.SUNNY;
            case "pcloudy", "pcloudyday", "pcloudynight", "windy", "windyday", "windynight", "humid" ->
                    WeatherState.PARTLY_CLOUDY;
            case "mcloudy", "mcloudynight", "mcloudyday" -> WeatherState.CLOUDY_S_SUNNY;
            case "cloudy", "cloudyday", "cloudynight" -> WeatherState.CLOUDY;
            case "fog", "fogday", "fognight", "humidnight", "humidday" -> WeatherState.FOG;
            case "lightrain", "lightrainday", "lightrainnight", "rain", "rainday", "rainnight" -> WeatherState.RAIN;
            case "oshower", "oshowerday", "oshowernight" -> WeatherState.RAIN_S_SUNNY;
            case "ishower", "ishowerday", "ishowernight" -> WeatherState.SUNNY_S_RAIN;
            case "lightsnow", "lightsnowday", "lightsnownight" -> WeatherState.SNOW_LIGHT;
            case "snow", "snowday", "snownight" -> WeatherState.SNOW;
            case "rainsnow", "rainsnowday", "rainsnownight" -> WeatherState.SLEET;
            case "ts", "tstorm", "tsrrain", "tsnight", "tsday" -> WeatherState.THUNDERSTORMS;
            default -> {
                System.out.println(weather + " is unknown");
                yield WeatherState.UNKNOWN;
            }
        };
    }
}
