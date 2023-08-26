package com.zenika.grenoble.nightclazz.java21.entities;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class City {

    @Id
    @NotBlank
    @Length(min = 2)
    private String name;

    @Embedded
    @Valid
    private GeoPosition position;

    public City(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPosition getPosition() {
        return position;
    }

    public void setPosition(GeoPosition position) {
        this.position = position;
    }
}
