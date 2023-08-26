package com.zenika.grenoble.nightclazz.java21.repositories.jpa;

import com.zenika.grenoble.nightclazz.java21.entities.City;
import com.zenika.grenoble.nightclazz.java21.repositories.CityRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepositoryJpa extends CityRepository, CrudRepository<City, String> {

}
