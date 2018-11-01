package com.example.jessi.omnibus.ui.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;
import com.example.jessi.omnibus.ui.payment.PaymentActivity;
import com.example.jessi.omnibus.util.AppController;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CouponActivity extends AppCompatActivity {
    private static final String TAG = "CouponActivity";
    @BindView(R.id.btn_coupon)
    Button btnCoupon;
    @BindView(R.id.cardView5)
    CardView cardView5;
    @BindView(R.id.et_coupon_no)
    EditText etCouponNo;
    @BindView(R.id.tv_coupon_message)
    TextView tvCouponMessage;

    SeatRequestResponse seatRequestResponse;

    boolean proper = true;
    private String busID;
    private int seatCount;
    private String url;
    private String couponNo;

    double dTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);

        busID = AppController.getInstance().getSP(this, "TABLE", "BusID");
        dTotal = Double.valueOf(AppController.getInstance().getSP(this, "TABLE", "Fare"))
                *  Double.valueOf(AppController.getInstance().getSP(this, "TABLE", "SeatCount")) ;
        int seatCount = Integer.valueOf(AppController.getInstance().getSP(this, "TABLE", "SeatCount"));
        String passengersInfo = "\n";
        for (int i =0; i < seatCount; i++)
        {
            String fName = AppController.getInstance().getSP(this, "TABLE", "FirstName_"+i);
            String lName = AppController.getInstance().getSP(this, "TABLE", "LastName_"+i);
            String age = AppController.getInstance().getSP(this, "TABLE", "Age_"+i);
            passengersInfo += "\nPassenger "
                    + (i+1)
                    + ": "
                    + fName
                    + " "
                    + lName;

        }
        tvCouponMessage.setText("Fare: $"+(dTotal*seatCount)+ "\n"+ passengersInfo);

        RxValidator.createFor(this.etCouponNo)
                .digitOnly()
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override
                    public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem().setError(etResult.isProper() ? null : etResult.getMessage());

                        if (!etResult.isProper()) {
                            proper = false;
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });
    }

    @OnClick(R.id.btn_coupon)
    public void onViewClicked() {
        Intent paymentIntent = new Intent(CouponActivity.this, PaymentActivity.class);

        if(proper || etCouponNo.getText().length() ==0)
        {
            couponNo = this.etCouponNo.getText().toString();
            AppController.getInstance().addSP(this, "TABLE", "CouponNo", couponNo);
            startActivity(paymentIntent);

        }
        else
        {
            Toast.makeText(this, "Coupon not 8 digits", Toast.LENGTH_SHORT).show();
        }
    }
}
