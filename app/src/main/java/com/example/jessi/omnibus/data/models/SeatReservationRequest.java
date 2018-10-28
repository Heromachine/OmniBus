package com.example.jessi.omnibus.data.models;

import com.example.jessi.omnibus.ui.seat.SeatContract;

import java.util.List;

public class SeatReservationRequest {
    private String BusID;
    private List<String> namesOfSeatSelected;
    private String seatURL;

    public SeatReservationRequest(String busID, List<String> namesOfSeatSelected) {
        BusID = busID;
        this.namesOfSeatSelected = namesOfSeatSelected;
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
