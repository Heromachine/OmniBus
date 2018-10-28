package com.example.jessi.omnibus.data.models;

public class BusInfoRequest {
    private String routeID;

    public BusInfoRequest(String routeID) {
        this.routeID = routeID;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }
}
