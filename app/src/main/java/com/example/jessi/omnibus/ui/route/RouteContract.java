package com.example.jessi.omnibus.ui.route;

import com.example.jessi.omnibus.data.models.RouteRequest;
import com.example.jessi.omnibus.data.models.Routes;

import java.util.List;

public interface RouteContract {

    interface View{
        void initListView(Routes routes);

        RouteRequest getRouteRequest();
    }
    interface Presenter{}
}
