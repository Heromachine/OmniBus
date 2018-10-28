package com.example.jessi.omnibus.data.models;

public class CityNamesModel {
    private String departureCityName;
    private String arrivalCityName;

    public CityNamesModel(String departureCityName, String arrivalCityName) {
        this.departureCityName = departureCityName;
        this.arrivalCityName = arrivalCityName;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }
}
