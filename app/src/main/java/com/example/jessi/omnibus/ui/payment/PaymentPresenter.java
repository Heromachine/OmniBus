package com.example.jessi.omnibus.ui.payment;

import android.content.Context;

import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.CouponRequest;
import com.example.jessi.omnibus.data.models.CouponValidation;
import com.example.jessi.omnibus.util.AppController;

public class PaymentPresenter implements PaymentContract.Presenter , IDataManger.CouponValidationOnResponseListener{
    PaymentContract.View view;
    Context context;
    IDataManger dataManger;


    public PaymentPresenter(PaymentActivity paymentActivity) {
        view = paymentActivity;
        context = paymentActivity;
        dataManger = new DataManager();
        String temp = AppController.getInstance().getSP(context, "TABLE", "CouponNo");
        if(temp.length() != 0)
        dataManger.networkRetrofitCallCouponValidation(this, view.getCouponValidationRequest());

    }


    @Override
    public void getCouponValidation(CouponValidation couponValidation) {
        view.setCouponValidation(couponValidation);
    }
}
