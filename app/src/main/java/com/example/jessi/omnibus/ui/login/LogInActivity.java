package com.example.jessi.omnibus.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.ForgotPWRequest;
import com.example.jessi.omnibus.data.models.ForgotPassword;
import com.example.jessi.omnibus.data.models.LogIn;
import com.example.jessi.omnibus.data.models.LoginRequest;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class LogInActivity extends AppCompatActivity implements LogInContract.View {

    private static final String TAG = "LogInActivity";

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



    private boolean validationPassed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        loginRequest = new LoginRequest();

        initRxValidator();

        loginModel = new LogIn();
        presenter = new LogInPresenter(this);
    }

    @OnClick({R.id.btn_login_log, R.id.btn_register_log, R.id.tv_forgot_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_log:

                if (loginRequest.isbSuccess())
                {
                    loginRequest.setMobile(etMobileLog.getText().toString());
                    loginRequest.setPassword(etPasswordLog.getText().toString());
                    presenter.onButtonClicked(view);
                    Toast.makeText
                            (this, "LoginModelOld Model: " + loginModel.getEmail(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_register_log:
                presenter.onButtonClicked(view);
                break;
            case R.id.tv_forgot_pw:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fpwframefrag, new ForgotPasswordFragment())
                        .addToBackStack(null)
                        .commit();
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

    @Override
    public ForgotPWRequest getPasswordRequest() {
        return null;
    }

    @Override
    public void setForgotPassword(ForgotPassword forgotPassword) {
        Toast.makeText(this, forgotPassword.getMsg(), Toast.LENGTH_SHORT).show();

    }

//
//    public static Observable<String> getTextWatcherObservable(@NonNull final EditText editText){
//        final PublishSubject<String> subject = PublishSubject.create();
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                subject.onNext(editable.toString());
//            }
//        });
//        return subject;
//    }

    public boolean isValidationPassed() {
        return validationPassed;
    }

    public void setValidationPassed(boolean validationPassed) {
        this.validationPassed = validationPassed;
    }

    private void initRxValidator ()
    {


        RxValidator.createFor(this.etMobileLog)
                .nonEmpty()
                .digitOnly()
                .minLength(10, "Format: 10 #s")
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem().setError(etResult.isProper() ? null : etResult.getMessage());
                        Log.i(TAG, "Validation etResult " + etResult.toString());
                        if(etResult.isProper())
                        {
                            loginRequest.setbSuccess(true);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(this.etPasswordLog)
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
                            loginRequest.setbSuccess(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }



}
