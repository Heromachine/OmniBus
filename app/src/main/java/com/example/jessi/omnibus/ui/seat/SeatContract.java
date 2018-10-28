package com.example.jessi.omnibus.ui.seat;

import com.example.jessi.omnibus.data.models.SeatInfoRequest;

import java.util.List;
import java.util.Vector;

public interface SeatContract {
    interface View{
        void initRecyclerView(List<Vector<String>> seatInfoList);
        SeatInfoRequest getSeatInfoRequest();

        List<String> getNameOfSeatSelected();
        void setNameOfSeatSelected(List<String> names);
        void removeSeatSelected(String name);
    }
    interface Presenter{

    }
}
