package com.example.jessi.omnibus.ui.login;

import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginModelOld;
import com.example.jessi.omnibus.data.models.LoginRequest;


public interface LogInContract {

    interface View{
        void setLoginModel(LogIn model);
        LogIn getLogIn();
        LoginRequest getLoginRequest();
    }
    interface Presenter{
        void onButtonClicked(android.view.View view);
    }

}
