package com.example.jessi.omnibus.ui.chekcout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.TextView;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.Coupons;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckOutActivity extends AppCompatActivity implements CheckOutContract.View{

    @BindView(R.id.tv_checkout_title)
    TextView tvCheckoutTitle;
    @BindView(R.id.tv_route_name)
    TextView tvRouteName;
    @BindView(R.id.tv_bus_id_invoice)
    TextView tvBusIdInvoice;
    @BindView(R.id.tv_fare_invoice)
    TextView tvFareInvoice;
    @BindView(R.id.tv_coupon_invoice)
    TextView tvCouponInvoice;
    @BindView(R.id.tv_service_tax)
    TextView tvServiceTax;
    @BindView(R.id.tv_journey_date)
    TextView tvJourneyDate;
    @BindView(R.id.tv_Boarding_time_invoice)
    TextView tvBoardingTimeInvoice;
    @BindView(R.id.tv_dropping_time_invoice)
    TextView tvDroppingTimeInvoice;
    @BindView(R.id.tv_duration_invoice)
    TextView tvDurationInvoice;
    @BindView(R.id.tv_passenger_no)
    TextView tvPassengerNo;
    @BindView(R.id.cardView3)
    CardView cardView3;
    @BindView(R.id.btn_purchase)
    Button btnPurchase;
    @BindView(R.id.cardView4)
    CardView cardView4;

    CheckOutContract.Presenter presenter;
    Coupons coupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        presenter = new CheckOutPresenter(this);
    }

    @OnClick(R.id.btn_purchase)
    public void onViewClicked() {
    }
}
