package com.example.jessi.omnibus.ui.passenger;

import com.example.jessi.omnibus.data.models.CouponRequest;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;

public interface PassengerContract  {
    interface View{
        void setSeatReservation(SeatRequestResponse seatRequestResponse);

        SeatReservationRequest getSeatReservationRequest();
    }
    interface Presenter{}
}
