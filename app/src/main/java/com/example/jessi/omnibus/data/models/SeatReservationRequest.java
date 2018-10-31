package com.example.jessi.omnibus.data.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatReservationRequest {
    private String BusID;
    private List<String> namesOfSeatSelected;
    private String seatURL ;
    Map<String, String> seatsMap;
    String finalURL;

    public String getFinalURL() {
        return finalURL = "http://rjtmobile.com/aamir/otr/android-app/chooseseat.php?busid="+this.getBusID()+seatURL;
    }

    public void setFinalURL(String finalURL) {
        this.finalURL = finalURL;
    }

    public SeatReservationRequest(String busID, List<String> namesOfSeatSelected) {
        BusID = busID;
        this.namesOfSeatSelected = namesOfSeatSelected;
        seatsMap = new HashMap<>();
    }

    public SeatReservationRequest() {
    }

    public Map<String, String> getSeatsMap() {

        return seatsMap;
    }

    public void setSeatsMap(Map<String, String> seatsMap) {
        this.seatsMap = seatsMap;
    }

    public String getBusID() {
        return BusID;
    }

    public void setBusID(String busID) {
        BusID = busID;
    }

    public List<String> getNamesOfSeatSelected() {
        return namesOfSeatSelected;
    }

    public void setNamesOfSeatSelected(List<String> namesOfSeatSelected) {
        this.namesOfSeatSelected = namesOfSeatSelected;
        for(int i = 0; i < namesOfSeatSelected.size(); i++)
        {
            seatURL = "&"+namesOfSeatSelected.get(i)+"=1";
        }
    }

    public String getSeatURL() {
        return seatURL;
    }

    public void setSeatURL(String seatURL) {
        this.seatURL = seatURL;
    }
}
