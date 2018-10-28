package com.example.jessi.omnibus.ui.registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jessi.omnibus.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View{

    @BindView(R.id.iv_reg_banner)
    ImageView ivRegBanner;
    @BindView(R.id.et_first_name_reg)
    EditText etFirstNameReg;
    @BindView(R.id.et_last_name_reg)
    EditText etLastNameReg;
    @BindView(R.id.et_address_reg)
    EditText etAddressReg;
    @BindView(R.id.et_email_reg)
    EditText etEmailReg;
    @BindView(R.id.et_mobile_reg)
    EditText etMobileReg;
    @BindView(R.id.et_password_reg)
    EditText etPasswordReg;
    @BindView(R.id.btn_register)
    Button btnRegister;

    RegistrationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        presenter = new RegistrationPresenter(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {

    }
}
