package com.example.jessi.omnibus.ui.registration;

import android.view.View;

import com.example.jessi.omnibus.data.models.RegistrationModel;
import com.example.jessi.omnibus.data.models.RegistrationRequest;

public interface RegistrationContract {
    interface Presenter{
        void onButtonClicked(android.view.View view);
    }
    interface View{
        void setRegistration(RegistrationModel registrationModel);

        RegistrationRequest getRegistrationRequest();
    }
}
