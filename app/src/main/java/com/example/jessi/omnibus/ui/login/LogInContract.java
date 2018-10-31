package com.example.jessi.omnibus.ui.login;

import com.example.jessi.omnibus.data.models.ForgotPWRequest;
import com.example.jessi.omnibus.data.models.ForgotPassword;
import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginModelOld;
import com.example.jessi.omnibus.data.models.LoginRequest;


public interface LogInContract {

    interface View{
        void setLoginModel(LogIn model);
        LogIn getLogIn();
        LoginRequest getLoginRequest();

        ForgotPWRequest getPasswordRequest();

        void setForgotPassword(ForgotPassword forgotPassword);
    }
    interface Presenter{
        void onButtonClicked(android.view.View view);
    }

}
