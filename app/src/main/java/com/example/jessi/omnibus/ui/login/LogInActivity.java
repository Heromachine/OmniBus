package com.example.jessi.omnibus.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginModelOld;
import com.example.jessi.omnibus.data.models.LoginRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends AppCompatActivity implements LogInContract.View {

    @BindView(R.id.iv_login_banner)
    ImageView ivLoginBanner;
    @BindView(R.id.tv_login_title)
    TextView tvLoginTitle;
    @BindView(R.id.et_mobile_log)
    EditText etMobileLog;
    @BindView(R.id.et_password_log)
    EditText etPasswordLog;
    @BindView(R.id.btn_login_log)
    Button btnLoginLog;
    @BindView(R.id.btn_register_log)
    Button btnRegisterLog;
    @BindView(R.id.tv_forgot_pw)
    TextView tvForgotPw;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    LogInContract.Presenter presenter;
    LoginRequest loginRequest;
    LogIn loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        loginModel = new LogIn();
        presenter = new LogInPresenter(this);
    }

    @OnClick({R.id.btn_login_log, R.id.btn_register_log, R.id.tv_forgot_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_log:
               loginRequest = new LoginRequest
                       (etMobileLog.getText().toString(), etPasswordLog.getText().toString());
               presenter.onButtonClicked(view);
                Toast.makeText
                        (this, "LoginModelOld Model: " + loginModel.getEmail(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_register_log:
                presenter.onButtonClicked(view);
                break;
            case R.id.tv_forgot_pw:
                presenter.onButtonClicked(view);
                break;
        }
    }

    @Override
    public void setLoginModel(LogIn model) {
        loginModel = model;
    }

    @Override
    public LogIn getLogIn() {
        return loginModel;
    }

    @Override
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }


}
