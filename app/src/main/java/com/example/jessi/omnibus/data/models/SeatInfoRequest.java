package com.example.jessi.omnibus.data.models;

import java.util.List;

public class SeatInfoRequest {

    private String BusID;

    public SeatInfoRequest(String busID) {
        BusID = busID;

    }

    public String getBusID() {
        return BusID;
    }

    public void setBusID(String busID) {
        BusID = busID;
    }


}
