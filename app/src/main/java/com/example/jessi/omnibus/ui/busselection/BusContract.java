package com.example.jessi.omnibus.ui.busselection;

import com.example.jessi.omnibus.data.models.BusInfoModel;
import com.example.jessi.omnibus.data.models.BusInfoRequest;

public interface BusContract {
    interface View{
        BusInfoRequest getBusInfoRequest();
        void initListView(BusInfoModel busInfoModel);
    }
    interface Presenter{}
}
