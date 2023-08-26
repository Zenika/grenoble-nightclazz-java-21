package com.zenika.grenoble.nightclazz.java21.repositories.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenika.grenoble.nightclazz.java21.entities.City;
import com.zenika.grenoble.nightclazz.java21.entities.DailyWeather;
import com.zenika.grenoble.nightclazz.java21.entities.HourlyWeather;
import com.zenika.grenoble.nightclazz.java21.repositories.WeatherRepository;
import com.zenika.grenoble.nightclazz.java21.repositories.http.dto.DailyWeather7Timer;
import com.zenika.grenoble.nightclazz.java21.repositories.http.dto.HourlyWeather7Timer;
import com.zenika.grenoble.nightclazz.java21.repositories.http.mapper.Weather7TimerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Repository
public class WeatherRepository7Timer implements WeatherRepository {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String weatherUrl;

    public WeatherRepository7Timer(RestTemplateBuilder restTemplateBuilder,
                                   ObjectMapper objectMapper,
                                   @Value("${weather.url}") String weatherUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
        this.weatherUrl = weatherUrl;
    }

    @Override
    public List<DailyWeather> getDailyWeather(City city) {
        String response = getResponse(city, "civillight");
        try {
            DailyWeather7Timer result = objectMapper.readValue(response, DailyWeather7Timer.class);
            return Weather7TimerMapper.toDomain(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HourlyWeather> getHourlyWeather(City city) {
        String response = getResponse(city, "civil");
        try {
            HourlyWeather7Timer result = objectMapper.readValue(response, HourlyWeather7Timer.class);
            return Weather7TimerMapper.toDomain(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(City city, String product) {
        String url = weatherUrl + "/" + product + ".php?lon=" + city.getPosition().getLongitude() +
                "&lat=" + city.getPosition().getLatitude() +
                "&ac=0&unit=metric&output=json&tzshift=0";
        return restTemplate.getForObject(url, String.class);
    }
}
