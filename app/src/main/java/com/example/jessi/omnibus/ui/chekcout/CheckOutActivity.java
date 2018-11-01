package com.example.jessi.omnibus.ui.chekcout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.CheckoutRequest;
import com.example.jessi.omnibus.data.models.Coupons;
import com.example.jessi.omnibus.ui.purchased.PurchasedActivity;
import com.example.jessi.omnibus.util.AppController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckOutActivity extends AppCompatActivity implements CheckOutContract.View {

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
    CheckoutRequest checkoutRequest;
    double fare;
    double discount;
    double tax;
    double total;
    @BindView(R.id.tv_total)
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        checkoutRequest = new CheckoutRequest();


        this.tvServiceTax.setText("Route: " + AppController.getInstance().getSP(this, "TABLE", "RouteName"));
        this.tvBusIdInvoice.setText("Bus ID: " + AppController.getInstance().getSP(this, "TABLE", "BusID"));
        this.tvJourneyDate.setText("Date: " + AppController.getInstance().getSP(this, "TABLE", "Date"));
        this.tvBoardingTimeInvoice.setText("Boarding: " + AppController.getInstance().getSP(this, "TABLE", "BoardingTime"));
        this.tvDroppingTimeInvoice.setText("Dropping: " + AppController.getInstance().getSP(this, "TABLE", "DroppingTime"));
        this.tvDurationInvoice.setText("Duration: " + AppController.getInstance().getSP(this, "TABLE", "Duration"));

        presenter = new CheckOutPresenter(this);

        int seatCount = Integer.valueOf(AppController.getInstance().getSP(this, "TABLE", "SeatCount"));

        String temp = "";
        for (int i = 0; i < seatCount; i++) {

            temp += "Passenger "+(i+1)+ ": "+AppController.getInstance().getSP(this, "TABLE", "FirstName_" + i);
            temp += " ";
            temp += AppController.getInstance().getSP(this, "TABLE", "LastName_" + i);//
            temp += ", Age: ";
            temp += AppController.getInstance().getSP(this, "TABLE", "Age_" + i);//
            temp += ", Gender: ";
            temp += AppController.getInstance().getSP(this, "TABLE", "Gender_" + i);//
            temp += ", Seat: ";
            temp += AppController.getInstance().getSP(this, "TABLE", "SeatNo_" + i);
        }

        fare = Double.valueOf(AppController.getInstance().getSP(this, "TABLE", "Fare"));

        if (AppController.getInstance().getSP(this, "TABLE", "Discount").isEmpty()) {
            discount = 0;
        } else {
            discount
                    = fare *
                    (Double.valueOf(
                            AppController
                                    .getInstance()
                                    .getSP(this, "TABLE", "Discount")) / 100);
        }
        tax = fare * .18;
        this.tvFareInvoice.setText("Fare: $" + fare);
        this.tvPassengerNo.setText(temp);
        this.tvCouponInvoice.setText("Coupon Discount: $" + discount);

        this.tvServiceTax.setText("Tax: $" + tax);
        total = fare + tax - discount;
        this.tvTotal.setText("Total: $" + total);


    }

    @OnClick(R.id.btn_purchase)
    public void onViewClicked(View view) {

        Intent intent = new Intent(CheckOutActivity.this, PurchasedActivity.class);
        startActivity(intent);
        presenter.onButtonClicked(view);
    }

    @Override
    public CheckoutRequest getCheckOutRequest() {
        return checkoutRequest;
    }

    @Override
    public void setCheckoutConfirmation(String string) {


    }
}
