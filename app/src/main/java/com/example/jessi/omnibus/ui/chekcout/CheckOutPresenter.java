package com.example.jessi.omnibus.ui.chekcout;

import android.content.Context;

import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;

public class CheckOutPresenter implements CheckOutContract.Presenter, IDataManger.CheckoutOnResposneListener {
    CheckOutContract.View view;
    Context context;
    IDataManger dataManger;

    public CheckOutPresenter(CheckOutActivity checkOutActivity) {
        view = checkOutActivity;
        context = checkOutActivity;
        dataManger = new DataManager();
        dataManger.networkRetrofitCallCheckOutInfo(this, view.getCheckOutRequest());
    }

    @Override
    public void getCheckoutConfirmation(String string) {

    }
}
