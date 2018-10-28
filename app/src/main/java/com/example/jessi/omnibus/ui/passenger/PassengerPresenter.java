package com.example.jessi.omnibus.ui.passenger;

public class PassengerPresenter implements PassengerContract.Presenter{
    PassengerContract.View view;

    public PassengerPresenter(PassengerActivity passengerActivity) {
        view = passengerActivity;
    }
}
