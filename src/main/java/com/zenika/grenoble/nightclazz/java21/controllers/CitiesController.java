package com.zenika.grenoble.nightclazz.java21.controllers;

import com.zenika.grenoble.nightclazz.java21.entities.City;
import com.zenika.grenoble.nightclazz.java21.repositories.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    private final CityRepository cityRepository;

    public CitiesController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<City> getCity(@PathVariable String name) {
        City city = cityRepository.getByName(name);
        if (city != null) {
            return ResponseEntity.ok(city);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City savedCity = cityRepository.save(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
    }
}
