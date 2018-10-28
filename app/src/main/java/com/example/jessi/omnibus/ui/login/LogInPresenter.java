package com.example.jessi.omnibus.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginModelOld;
import com.example.jessi.omnibus.ui.registration.RegistrationActivity;
import com.example.jessi.omnibus.ui.search.SearchActivity;

public class LogInPresenter implements  LogInContract.Presenter, IDataManger.LogInOnResponseListener {

    LogInContract.View view ;
    Context context;
    IDataManger dataManger;

    public LogInPresenter(LogInActivity logInActivity) {
        view = logInActivity;
        context = logInActivity;
        dataManger = new DataManager();
    }

    @Override
    public void onButtonClicked(View view) {
        switch (view.getId()){
            case R.id.btn_login_log:
                Toast.makeText(view.getContext(), "LoginModelOld Clicked", Toast.LENGTH_SHORT).show();
                dataManger.networkRetrofitCallLogin(this, this.view.getLoginRequest());
                Intent intentSearch = new Intent(context, SearchActivity.class);
                context.startActivity(intentSearch);
                break;
            case R.id.btn_register_log:
                Intent intentReg = new Intent(context, RegistrationActivity.class);
                context.startActivity(intentReg);
                break;
            case R.id.tv_forgot_pw:
                Toast.makeText(view.getContext(), "Forgot Password Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void passLogin(LogIn logIn) {
        view.setLoginModel(logIn);
    }
}
