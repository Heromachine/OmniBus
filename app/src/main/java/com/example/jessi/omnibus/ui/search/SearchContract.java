package com.example.jessi.omnibus.ui.search;

import com.example.jessi.omnibus.data.models.CityNamesModel;
import com.example.jessi.omnibus.data.models.CitiesModel;

public interface SearchContract {

    interface View{
        void setCitiesModel(CitiesModel citiesModel);
        CitiesModel getCitiesModel();
        CityNamesModel getCityNames();

    }
    interface Presenter{
        void onButtonClicked(android.view.View view);
    }
}
