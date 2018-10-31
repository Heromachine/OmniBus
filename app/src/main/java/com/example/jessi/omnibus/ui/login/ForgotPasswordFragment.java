package com.example.jessi.omnibus.ui.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.ForgotPWRequest;
import com.example.jessi.omnibus.data.models.ForgotPassword;

public class ForgotPasswordFragment extends Fragment implements IDataManger.ForgotPWOnResponseListener{
    EditText etForgotPWMobile;
    Button btnForgotPWReset;
    ForgotPWRequest forgotPWRequest;
    ForgotPassword forgotPassword;
    IDataManger dataManger;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        forgotPWRequest = new ForgotPWRequest();
        etForgotPWMobile = view.findViewById(R.id.et_forgotPW_Mobile);
        btnForgotPWReset = view.findViewById(R.id.btn_reset);
        dataManger = new DataManager();
        btnForgotPWReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPWRequest.setMobile(etForgotPWMobile.getText().toString());
                dataManger.networkRetrofitCallForgotPW(ForgotPasswordFragment.this, forgotPWRequest);
                Toast.makeText(getActivity(), "Message Sent", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    @Override
    public void getForgotPWModel(ForgotPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
        Toast.makeText(getActivity(), forgotPassword.getMsg(), Toast.LENGTH_SHORT).show();
    }
}


