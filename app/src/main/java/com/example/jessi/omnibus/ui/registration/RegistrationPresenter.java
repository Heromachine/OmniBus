package com.example.jessi.omnibus.ui.registration;

import com.example.jessi.omnibus.ui.search.SearchContract;

public class RegistrationPresenter implements RegistrationContract.Presenter{
    RegistrationContract.View view;

    public RegistrationPresenter(RegistrationActivity registrationActivity) {
        this.view = registrationActivity;
    }
}
