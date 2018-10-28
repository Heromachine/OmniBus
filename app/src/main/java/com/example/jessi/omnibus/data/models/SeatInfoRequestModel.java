package com.example.jessi.omnibus.data.models;

public class SeatInfoRequestModel {

    private String busID;

    public SeatInfoRequestModel(String busID) {
        this.busID = busID;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }
}
