package com.example.jessi.omnibus.ui.passenger;

import android.content.Context;

import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;

public class PassengerPresenter implements PassengerContract.Presenter, IDataManger.SeatReserveConfirmOnResponseListener {
    PassengerContract.View view;
    Context context;
    IDataManger dataManger;

    public PassengerPresenter(PassengerActivity passengerActivity) {
        context = passengerActivity;
        view = passengerActivity;
        dataManger = new DataManager();
        dataManger.networkRetrofitCallSeatReservation(this, view.getSeatReservationRequest());
    }


    @Override
    public void getSeatReservConfirm(SeatRequestResponse seatRequestResponse) {
        view.setSeatReservation(seatRequestResponse);
    }
}
