package com.zenika.grenoble.nightclazz.java21.repositories;

import com.zenika.grenoble.nightclazz.java21.entities.City;

import java.util.List;

public interface CityRepository {
    public List<City> findAll();
    public City getByName(String string);
    public City save(City city);
}
