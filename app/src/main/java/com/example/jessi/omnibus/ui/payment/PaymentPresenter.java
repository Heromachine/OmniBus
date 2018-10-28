package com.example.jessi.omnibus.ui.payment;

public class PaymentPresenter implements PaymentContract.Presenter {
    PaymentContract.View view;

    public PaymentPresenter(PaymentActivity paymentActivity) {
        view = paymentActivity;
    }
}
