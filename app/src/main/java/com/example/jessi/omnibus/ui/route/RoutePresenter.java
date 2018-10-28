package com.example.jessi.omnibus.ui.route;

import android.content.Context;
import android.util.Log;

import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.Routes;

public class RoutePresenter implements RouteContract.Presenter, IDataManger.RoutesOnResponseListener {
    private static final String TAG = "RoutePresenter";
    RouteContract.View view;
    Context context;
    IDataManger dataManger;


    public RoutePresenter(RouteActivity routeActivity) {
        Log.d(TAG, "RoutePresenter: ");
        this.view = routeActivity;
        context = routeActivity;
        dataManger = new DataManager();
        dataManger.networkRetrofitCallRoutes(this, this.view.getRouteRequest());
    }

    @Override
    public void getRoutesModel(Routes routes) {
        view.initListView(routes);
        Log.d(TAG, "getRoutesModel: " + routes.toString());
    }
}
