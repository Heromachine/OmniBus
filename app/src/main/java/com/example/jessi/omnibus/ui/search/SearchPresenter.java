package com.example.jessi.omnibus.ui.search;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.CitiesModel;
import com.example.jessi.omnibus.data.models.CityNamesModel;
import com.example.jessi.omnibus.data.models.RegistrationModel;
import com.example.jessi.omnibus.data.models.RouteRequest;
import com.example.jessi.omnibus.ui.busselection.BusActivity;
import com.example.jessi.omnibus.ui.route.RouteActivity;
import com.example.jessi.omnibus.util.AppController;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter, IDataManger.CitiesOnResponseListener {
    private static final String TAG = "SearchPresenter";
    SearchContract.View view;
    Context context;
    IDataManger dataManger;
    List<String> coordinates;

    public SearchPresenter(SearchActivity searchActivity) {
        view = searchActivity;
        context = searchActivity;
        dataManger = new DataManager();
        dataManger.networkRetrofitCallCities(this);
    }

    @Override
    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:

                if (this.view.getCityNames().getDepartureCityName().contentEquals("")
                        ||
                        this.view.getCityNames().getDepartureCityName().contentEquals("")) {
                    Toast.makeText(context, "Please Enter Both City Names", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(view.getContext(), "Search Was pressed", Toast.LENGTH_SHORT).show();
                    coordinates = prepareCoordinates(this.view.getCityNames(), this.view.getCitiesModel());
                    Intent activity = new Intent(context, RouteActivity.class);
                    activity.putExtra("StartLat", coordinates.get(0));
                    activity.putExtra("StartLon", coordinates.get(1));
                    activity.putExtra("EndLat", coordinates.get(2));
                    activity.putExtra("EndLon", coordinates.get(3));

                    AppController.getInstance().addSP(context, "TABLE", "StartLat",  coordinates.get(0));
                    AppController.getInstance().addSP(context, "TABLE", "StartLon",  coordinates.get(1));
                    AppController.getInstance().addSP(context, "TABLE", "EndLat",  coordinates.get(2));
                    AppController.getInstance().addSP(context, "TABLE", "EndLon",  coordinates.get(3));

                    context.startActivity(activity);
                }
                break;
        }
    }

    @Override
    public void setDateSelected(String dateSelected) {

    }

    @Override
    public void setCitiesModel(CitiesModel citiesModel) {


        Log.d(TAG, "setCitiesModel: cityModels size: " + citiesModel.getCity().size());

        view.setCitiesModel(citiesModel);

    }

    private List<String> prepareCoordinates(CityNamesModel cityNamesModel, CitiesModel citiesModel) {
        List<String> coordinates = new ArrayList<>();

        for (int i = 0; i < citiesModel.getCity().size(); i++) {
            if (citiesModel.getCity().get(i).getCityname().contentEquals(cityNamesModel.getArrivalCityName())) {
                coordinates.add(citiesModel.getCity().get(i).getCitylatitude());
                coordinates.add(citiesModel.getCity().get(i).getCitylongtitude());
            }
        }

        for (int i = 0; i < citiesModel.getCity().size(); i++) {
            if (citiesModel.getCity().get(i).getCityname().contentEquals(cityNamesModel.getDepartureCityName())) {
                coordinates.add(citiesModel.getCity().get(i).getCitylatitude());
                coordinates.add(citiesModel.getCity().get(i).getCitylongtitude());
            }
        }
        return coordinates;
    }
}
