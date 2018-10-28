package com.example.jessi.omnibus.ui.passenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jessi.omnibus.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PassengerActivity extends AppCompatActivity implements PassengerContract.View{

    @BindView(R.id.tv_contact_info)
    TextView tvContactInfo;
    @BindView(R.id.et_contact_email)
    EditText etContactEmail;
    @BindView(R.id.et_mobile_passenger)
    EditText etMobilePassenger;
    @BindView(R.id.tv_passenger_title)
    TextView tvPassengerTitle;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.btn_add_passengers)
    Button btnAddPassengers;
    PassengerContract.Presenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        ButterKnife.bind(this);

        presenter = new PassengerPresenter(this);
    }

    @OnClick(R.id.btn_add_passengers)
    public void onViewClicked() {
    }
}
