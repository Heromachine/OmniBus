package com.example.jessi.omnibus.ui.passenger;

import android.content.Context;
import android.view.View;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;

public class PassengerPresenter implements PassengerContract.Presenter, IDataManger.SeatReserveConfirmOnResponseListener {
    PassengerContract.iView iView;
    Context context;
    IDataManger dataManger;

    public PassengerPresenter(PassengerActivity passengerActivity) {
        context = passengerActivity;
        iView = passengerActivity;
        dataManger = new DataManager();

    }


    @Override
    public void getSeatReservConfirm(SeatRequestResponse seatRequestResponse) {
        iView.setSeatReservation(seatRequestResponse);
    }


    @Override
    public void onButtonClicked(View view) {
        switch(view.getId())
        {
            case R.id.btn_add_passengers:
                dataManger.networkRetrofitCallSeatReservation(this, iView.getSeatReservationRequest());
                break;
        }
    }
}
