package com.zenika.grenoble.nightclazz.java21.repositories.http.dto;

import java.util.List;

public class HourlyWeather7Timer {

    private List<DataSeries> dataseries;

    public HourlyWeather7Timer() {}

    public HourlyWeather7Timer(List<DataSeries> dataseries) {
        this.dataseries = dataseries;
    }

    public List<DataSeries> getDataseries() {
        return dataseries;
    }

    public void setDataseries(List<DataSeries> dataseries) {
        this.dataseries = dataseries;
    }

    public static class DataSeries {
        private Integer timepoint;
        private Double temp2m;
        private String weather;
        private Wind10m wind10m;

        public DataSeries(Integer timepoint, Double temp2m, String weather, Wind10m wind10m) {
            this.timepoint = timepoint;
            this.temp2m = temp2m;
            this.weather = weather;
            this.wind10m = wind10m;
        }

        public Integer getTimepoint() {
            return timepoint;
        }

        public void setTimepoint(Integer timepoint) {
            this.timepoint = timepoint;
        }

        public Double getTemp2m() {
            return temp2m;
        }

        public void setTemp2m(Double temp2m) {
            this.temp2m = temp2m;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public Wind10m getWind10m() {
            return wind10m;
        }

        public void setWind10m(Wind10m wind10m) {
            this.wind10m = wind10m;
        }
    }

    public static class Wind10m {
        private String direction;
        private Double speed;

        public Wind10m(String direction, Double speed) {
            this.direction = direction;
            this.speed = speed;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }
    }
}
