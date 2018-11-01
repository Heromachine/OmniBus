package com.example.jessi.omnibus.ui.passenger;

import android.view.View;

import com.example.jessi.omnibus.data.models.SeatRequestResponse;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;
import com.example.jessi.omnibus.ui.seat.SeatContract;

public interface PassengerContract  {
    interface iView {
        void setSeatReservation(SeatRequestResponse seatRequestResponse);

        SeatReservationRequest getSeatReservationRequest();
    }
    interface Presenter{
        void onButtonClicked(View view);
    }
}
