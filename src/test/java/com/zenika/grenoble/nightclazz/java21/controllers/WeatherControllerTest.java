package com.zenika.grenoble.nightclazz.java21.controllers;

import com.zenika.grenoble.nightclazz.java21.config.SecurityConfig;
import com.zenika.grenoble.nightclazz.java21.repositories.WeatherRepositoryInMemory;
import com.zenika.grenoble.nightclazz.java21.services.WeatherService;
import com.zenika.grenoble.nightclazz.java21.utils.JsonLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WeatherController.class)
@AutoConfigureDataJpa
@Transactional
@Import({WeatherService.class, WeatherRepositoryInMemory.class, SecurityConfig.class})
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getWeatherForKnownCityReturn200() throws Exception {
        mockMvc.perform(get("/cities/GRENOBLE/weather/daily"))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonLoader.from("/contract/weather/GET_DAILY_WEATHER.json")));
    }

    @Test
    public void getWeatherForUnknownCityReturn404() throws Exception {
        mockMvc.perform(get("/cities/{cityId}/weather", "NOWHERE"))
                .andExpect(status().isNotFound());
    }

}
