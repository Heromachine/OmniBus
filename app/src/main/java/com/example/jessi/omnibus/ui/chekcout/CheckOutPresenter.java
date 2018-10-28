package com.example.jessi.omnibus.ui.chekcout;

public class CheckOutPresenter implements CheckOutContract.Presenter{
    CheckOutContract.View view;

    public CheckOutPresenter(CheckOutActivity checkOutActivity) {
        view = checkOutActivity;
    }
}
