package com.example.jessi.omnibus.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.RegistrationModel;
import com.example.jessi.omnibus.data.models.RegistrationRequest;
import com.example.jessi.omnibus.ui.login.LogInActivity;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View{
    private static final String TAG = "RegistrationActivity";
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
    RegistrationRequest registrationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registrationRequest = new RegistrationRequest();
        ButterKnife.bind(this);
        initRxValidator();
        presenter = new RegistrationPresenter(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                if(registrationRequest.isValid()){
                    presenter.onButtonClicked(view);
                }
                else
                {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void setRegistration(RegistrationModel registrationModel) {
        Toast.makeText(this, ""+registrationModel.getResponse(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "setRegistration: " + registrationModel.getResponse());

        if(registrationModel.getResponse().contentEquals("successfully")){
            Intent intent = new Intent(RegistrationActivity.this, LogInActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public RegistrationRequest getRegistrationRequest() {
        registrationRequest = new RegistrationRequest(
                this.etFirstNameReg.getText().toString(),
                this.etLastNameReg.getText().toString(),
                this.etAddressReg.getText().toString(),
                this.etEmailReg.getText().toString(),
                this.etMobileReg.getText().toString(),
                this.etPasswordReg.getText().toString());
        return this.registrationRequest;
    }

    private void initRxValidator(){

        RxValidator.createFor(this.etAddressReg)
                .nonEmpty()
                .maxLength(20)
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem().setError(etResult.isProper() ? null : etResult.getMessage());
                        Log.i(TAG, "Validation etResult " + etResult.toString());
                        if(etResult.isProper())
                        {
                           registrationRequest.setValid(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(this.etFirstNameReg)
                .nonEmpty()
                .minLength(2, "Length 2+")
                .maxLength(15)
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override
                    public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem()
                                .setError(etResult
                                        .isProper() ? null :
                                        etResult
                                                .getMessage());

                        if(etResult.isProper())
                        {
                            registrationRequest.setValid(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(this.etLastNameReg)
                .nonEmpty()
                .minLength(2, "Length 2+")
                .maxLength(15)
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override
                    public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem()
                                .setError(etResult
                                        .isProper() ? null :
                                        etResult
                                                .getMessage());
                        if(etResult.isProper())
                        {
                            registrationRequest.setValid(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(this.etEmailReg)
                .nonEmpty()
                .email()
                .digitOnly()
                .minLength(9)
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem().setError(etResult.isProper() ? null : etResult.getMessage());
                        if(etResult.isProper())
                        {
                            registrationRequest.setValid(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(this.etMobileReg)
                .nonEmpty()
                .minLength(10, "10 #s")
                .digitOnly()
                .maxLength(10)
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override
                    public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem()
                                .setError(etResult
                                        .isProper() ? null :
                                        etResult
                                                .getMessage());
                        if(etResult.isProper())
                        {
                            registrationRequest.setValid(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(this.etPasswordReg)
                .nonEmpty()
                .minLength(8, "Length 8+")
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override
                    public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem()
                                .setError(etResult
                                        .isProper() ? null :
                                        etResult
                                                .getMessage());
                        if(etResult.isProper())
                        {
                            registrationRequest.setValid(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

    }
}
