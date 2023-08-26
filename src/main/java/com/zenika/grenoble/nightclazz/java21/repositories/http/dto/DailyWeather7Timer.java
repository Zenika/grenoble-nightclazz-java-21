package com.zenika.grenoble.nightclazz.java21.repositories.http.dto;

import java.util.List;

public class DailyWeather7Timer {

    private List<DataSeries> dataseries;

    public DailyWeather7Timer() {}

    public DailyWeather7Timer(List<DataSeries> dataseries) {
        this.dataseries = dataseries;
    }

    public List<DataSeries> getDataseries() {
        return dataseries;
    }

    public void setDataseries(List<DataSeries> dataseries) {
        this.dataseries = dataseries;
    }

    public static class DataSeries {
        private String date;
        private Temp2m temp2m;
        private String weather;
        private Double wind10m_max;

        public DataSeries(String date, Temp2m temp2m, String weather, Double wind10m_max) {
            this.date = date;
            this.temp2m = temp2m;
            this.weather = weather;
            this.wind10m_max = wind10m_max;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Temp2m getTemp2m() {
            return temp2m;
        }

        public void setTemp2m(Temp2m temp2m) {
            this.temp2m = temp2m;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public Double getWind10m_max() {
            return wind10m_max;
        }

        public void setWind10m_max(Double wind10m_max) {
            this.wind10m_max = wind10m_max;
        }
    }

    public static class Temp2m {
        private Double max;
        private Double min;

        public Temp2m(Double max, Double min) {
            this.max = max;
            this.min = min;
        }

        public Double getMax() {
            return max;
        }

        public void setMax(Double max) {
            this.max = max;
        }

        public Double getMin() {
            return min;
        }

        public void setMin(Double min) {
            this.min = min;
        }
    }
}
