package com.zenika.grenoble.nightclazz.java21.controllers;

import com.zenika.grenoble.nightclazz.java21.config.SecurityConfig;
import com.zenika.grenoble.nightclazz.java21.utils.JsonLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CitiesController.class)
@AutoConfigureDataJpa
@Transactional
@Testcontainers
@Import(SecurityConfig.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllCitiesReturn200() throws Exception {
        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonLoader.from("/contract/cities/GET_CITIES.json")));
    }

    @Test
    public void getExistingCityReturn200() throws Exception {
        mockMvc.perform(get("/cities/GRENOBLE"))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonLoader.from("/contract/cities/GET_CITY.json")));
    }

    @Test
    @WithMockUser(username = "ADMIN", roles = "ADMIN")
    public void addNewCityReturn201() throws Exception {
        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonLoader.from("/contract/cities/POST_CITY.json")))
                .andExpect(status().isCreated())
                .andExpect(content().json(JsonLoader.from("/contract/cities/POST_CITY.json")));
    }

    @Test
    public void addNewCityWithoutLoginReturn401() throws Exception {
        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonLoader.from("/contract/cities/POST_CITY.json")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void addInvalidCityReturn400() throws Exception {
        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonLoader.from("/contract/cities/POST_INVALID_CITY.json")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getUnknownCityReturn404() throws Exception {
        mockMvc.perform(get("/cities/NOWHERE"))
                .andExpect(status().isNotFound());
    }
}
