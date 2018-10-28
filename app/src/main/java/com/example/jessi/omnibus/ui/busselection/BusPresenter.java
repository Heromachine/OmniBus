package com.example.jessi.omnibus.ui.busselection;

import android.content.Context;

import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.BusInfoModel;

public class BusPresenter implements BusContract.Presenter, IDataManger.BusInfoOnResponseListener{
    BusContract.View view;
    Context context;
    IDataManger dataManger;

    public BusPresenter(BusActivity busActivity) {
        view = busActivity;
        context = busActivity;
        dataManger = new DataManager();
        dataManger.networkRetrofitCallBusInfo(this, view.getBusInfoRequest());
    }

    @Override
    public void getBusInfoModel(BusInfoModel busInfoModel) {
        view.initListView(busInfoModel);
    }
}
