package com.example.jessi.omnibus.ui.payment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.ui.passenger.PassengerContract;
import com.simplify.android.sdk.CardEditor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View {

    @BindView(R.id.tv_payment_title)
    TextView tvPaymentTitle;
    @BindView(R.id.iv_omnibus_icon)
    ImageView ivOmniBusIcon;
    @BindView(R.id.card_editor)
    CardEditor cardEditor;
    @BindView(R.id.btn_pay)
    Button btnPay;

    PaymentContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        presenter = new PaymentPresenter(this);
    }
}
