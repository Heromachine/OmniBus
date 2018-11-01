package com.example.jessi.omnibus.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.CouponRequest;
import com.example.jessi.omnibus.data.models.CouponValidation;
import com.example.jessi.omnibus.ui.chekcout.CheckOutActivity;
import com.example.jessi.omnibus.ui.passenger.PassengerContract;
import com.example.jessi.omnibus.util.AppController;
import com.simplify.android.sdk.Card;
import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View {

    private static final String TAG = "PaymentActivity";

    PaymentContract.Presenter presenter;
    Simplify simplify;
    CouponRequest CouponNumber;
    CouponValidation couponValidation;

    @BindView(R.id.tv_payment_title)
    TextView tvPaymentTitle;
    @BindView(R.id.iv_omnibus_icon)
    ImageView ivOmniBusIcon;
//    @BindView(R.id.card_editor)
//    CardEditor cardEditor;
//    @BindView(R.id.btn_pay)
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        CouponNumber = new CouponRequest(AppController.getInstance().getSP(this, "TABLE", "CouponNo"));
        Log.d(TAG, "onCreate: Coupog Number" + CouponNumber.getCouponNo());
        presenter = new PaymentPresenter(PaymentActivity.this);

        simplify = new Simplify();
        simplify.setApiKey("sbpb_OWNmMWQ1YjUtNjMzNy00N2I3LTk2NjQtZDk4MmYzZTE1MWJh");

       btnPay = (Button) findViewById(R.id.btn_pay);
//
//        cardEditor = (CardEditor) findViewById(R.id.card_editor);
        final CardEditor cardEditor = (CardEditor) findViewById(R.id.card_editor);
        cardEditor.addOnStateChangedListener(new CardEditor.OnStateChangedListener() {
            @Override
            public void onStateChange(CardEditor cardEditor) {
                // isValid() == true : card editor contains valid and complete card information
                btnPay.setEnabled(cardEditor.isValid());
            }
        });
        
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simplify.createCardToken(cardEditor.getCard(), new CardToken.Callback() {
                    @Override
                    public void onSuccess(CardToken cardToken) {
                        Toast.makeText(PaymentActivity.this, "Payment Made", Toast.LENGTH_SHORT).show();
                        Intent checkoutInent
                                = new Intent
                                (PaymentActivity.this, CheckOutActivity.class);
                        startActivity(checkoutInent);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(PaymentActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public CouponRequest getCouponValidationRequest() {
        return CouponNumber;
    }

    @Override
    public void setCouponValidation(CouponValidation couponValidation) {
        Log.d(TAG, "setCouponValidation: Coupon Validation: " + couponValidation.getMsg());
        Log.d(TAG, "setCouponValidation: Coupon Discount: " +  couponValidation.getDiscount());

        if(couponValidation.getMsg().contentEquals("no coupon found"))
        {
            AppController.getInstance().addSP(PaymentActivity.this, "TABLE", "Discount", "0");
            Toast.makeText(this,  couponValidation.getMsg(), Toast.LENGTH_SHORT).show();
        }
        else if(couponValidation.getMsg().contentEquals("success"))
        {
        {
            Toast.makeText(this,  couponValidation.getMsg(), Toast.LENGTH_SHORT).show();
        }
            AppController.getInstance().addSP(PaymentActivity.this, "TABLE", "Discount", couponValidation.getDiscount());
        }
        
        this.couponValidation = couponValidation;

    }
}
