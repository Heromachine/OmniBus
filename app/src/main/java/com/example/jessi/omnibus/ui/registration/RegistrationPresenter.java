package com.example.jessi.omnibus.ui.registration;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.RegistrationModel;
import com.example.jessi.omnibus.ui.search.SearchContract;

public class RegistrationPresenter implements RegistrationContract.Presenter, IDataManger.RegistrationOnResponseListener{
    RegistrationContract.View view;
    Context context;
    IDataManger dataManger;
    public RegistrationPresenter(RegistrationActivity registrationActivity) {
        this.view = registrationActivity;
        context = registrationActivity;
        dataManger = new DataManager();

    }

    @Override
    public void onButtonClicked(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                dataManger.networkRetrofitCallRegistration(this, this.view.getRegistrationRequest());
                break;
        }

    }

    @Override
    public void getRegistrationModel(RegistrationModel registrationModel) {
        view.setRegistration(registrationModel);
    }
}
