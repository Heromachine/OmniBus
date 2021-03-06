package com.example.jessi.omnibus.ui.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.Passenger;
import com.example.jessi.omnibus.data.models.SeatRequestResponse;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;
import com.example.jessi.omnibus.ui.coupon.CouponActivity;
import com.example.jessi.omnibus.ui.seat.SeatsActivity;
import com.example.jessi.omnibus.util.AppController;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PassengerActivity extends AppCompatActivity implements PassengerContract.iView {
    private static final String TAG = "PassengerActivity";
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
    private int seatCount = 0;

    List<Passenger> passengerList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    PassengerAdapter passengerAdapter;

    Passenger isValid;
    private int Discount;
    private String url = "";
    private String busID;
    private SeatReservationRequest seatReservationRequest;
    private Intent couponActivity;
    boolean confirmed = false;
    boolean set = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        ButterKnife.bind(this);
        couponActivity = new Intent(PassengerActivity.this, CouponActivity.class);
        busID = AppController.getInstance().getSP(this, "TABLE", "BusID");
        seatCount = Integer.valueOf(AppController.getInstance().getSP(this, "TABLE", "SeatCount"));
        passengerList = new ArrayList<>();
        seatReservationRequest = new SeatReservationRequest();
        btnAddPassengers.setText("UpdateList");

        Map<String, String> tempMap = new HashMap<>();
        for(int i = 0; i < seatCount; i++)
        {
            Passenger temp = new Passenger();
            temp.setSeat(AppController
                    .getInstance()
                    .getSP(
                            PassengerActivity.this,
                            "TABLE",
                            "SeatName_"+i ));
            passengerList.add(temp);

            url += ChangeName(AppController
                    .getInstance()
                    .getSP(
                            PassengerActivity.this,
                            "TABLE",
                            "SeatName_"+i ));

//            url += "&"+AppController
//                    .getInstance()
//                    .getSP(
//                            PassengerActivity.this,
//                            "TABLE",
//                            "SeatName_"+i ) + "=1";
        }

        seatReservationRequest.setSeatsMap(tempMap);
        seatReservationRequest.setSeatURL(url);
        seatReservationRequest.setBusID(busID);

        isValid = new Passenger();

        initRxValidator();
        initRecyclerView(passengerList);
        presenter = new PassengerPresenter(this);
    }

    @OnClick(R.id.btn_add_passengers)
    public void onViewClicked(View view) {
        passengerList = passengerAdapter.getPassengerList();

        if (seatCount != passengerAdapter.getSeatConfirmed())
        {
            Toast.makeText(this, "Info Missing", Toast.LENGTH_SHORT).show();
        }else
        {
            if (isValid.isValid() || passengerAdapter.isValid())
            {
                passengerList = passengerAdapter.getPassengerList();
                for(int i = 0; i < passengerList.size(); i ++)
                {
                    Log.d(TAG, "onViewClicked: LN"+ passengerList.get(i).getLastName());
                    Log.d(TAG, "onViewClicked: FN"+ passengerList.get(i).getFirstName());
                    AppController
                            .getInstance()
                            .addSP(this,
                                    "TABLE",
                                    "FirstName_" + i,
                                    passengerList.get(i).getFirstName() );

                    AppController
                            .getInstance()
                            .addSP(this,
                                    "TABLE",
                                    "LastName_"+ i,
                                    passengerList.get(i).getLastName() );
                    AppController
                            .getInstance()
                            .addSP(this,
                                    "TABLE",
                                    "Age_" + i,
                                    passengerList.get(i).getAge() );
                    AppController
                            .getInstance()
                            .addSP(this,
                                    "TABLE",
                                    "Gender_" + i,
                                    passengerList.get(i).getGender() );


                    AppController
                            .getInstance()
                            .addSP(this,
                                    "TABLE",
                                    "SeatNo_" + i,
                                    passengerList.get(i).getGender() );
                }

                presenter.onButtonClicked(view);
            }

        }

    }
    @Override
    public void setSeatReservation(SeatRequestResponse seatRequestResponse) {
        Log.d(TAG, "setSeatReservation: "+ seatRequestResponse.getMsg().get(0));
        if (seatRequestResponse.getMsg().get(0).contains("reserved"))
        {
            Toast.makeText(this, "Reserved!", Toast.LENGTH_SHORT).show();
            startActivity(couponActivity);
        }
        else if(seatRequestResponse.getMsg().get(0).contains("already reserved"))
        {
            Intent seatActivity = new Intent(PassengerActivity.this, SeatsActivity.class);
            Toast.makeText(this, "Seats Reserved", Toast.LENGTH_SHORT).show();
            startActivity(seatActivity);
        }
    }

    public void initRecyclerView(List<Passenger> passengerList){
        layoutManager = new LinearLayoutManager(PassengerActivity.this);
        passengerAdapter = new PassengerAdapter(PassengerActivity.this, passengerList);
        adapter = passengerAdapter;
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(adapter);
    }

    private void initRxValidator(){
        RxValidator.createFor(this.etContactEmail)
                .nonEmpty()
                .email()
                .minLength(9)
                .onFocusChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxValidationResult<EditText>>() {
                    @Override public void call(RxValidationResult<EditText> etResult) {
                        etResult.getItem().setError(etResult.isProper() ? null : etResult.getMessage());
                        if(etResult.isProper())
                        {
                            isValid.setValid(true);
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "Validation error", throwable);
                    }
                });

        RxValidator.createFor(this.etMobilePassenger)
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
                            isValid.setValid(true);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    @Override
    public SeatReservationRequest getSeatReservationRequest() {
        return seatReservationRequest;
    }


    public String ChangeName(String name)
    {
        StringBuilder temp = new StringBuilder();
        Log.d(TAG, "ChangeName: name ="+ name);
        int seatNumber;
        for(int i = name.length(); i > 0; i--)
        {
            if (name.charAt(i -1) == 's'){
                break;
            }
            else {

                temp.insert(0, name.charAt(i-1)) ;
            }
        }
        seatNumber = Integer.valueOf(temp.toString());
        Log.d(TAG, "ChangeName: seatNumber = "+ seatNumber);
        List<String> seats = Arrays.asList(
                "seatone",
                "seattwo",
                "seatthree",
                "seatfour",
                "seatfive",
                "seatsix",
                "seatseven",
                "seateight",
                "seatnine",
                "seatten",
                "seateleven",
                "seattwelve",
                "seatthirteen",
                "seatfourteen",
                "seatfifteen",
                "seatsixteen",
                "seatseventeen",
                "seateighteen",
                "seatnineteen",
                "seattwenty",
                "seattwentyone",
                "seattwentytwo",
                "seattwentythree",
                "seattwentyfour",
                "seattwentyfive",
                "seattwentysix",
                "seattwentyseven",
                "seattwentyeight",
                "seattwentynine",
                "seatthirty",
                "seatthirtyone",
                "seatthirtytwo ",
                "seatthirtythree",
                "seatthirtyfour",
                "seatthirtyfive",
                "seatthirtysix",
                "seatthirtyseven",
                "seatthirtyeight",
                "seatthirtynine",
                "seatforty",
                "seatfortyone",
                "seatfortytwo",
                "seatfortytwo",
                "seatfortythree",
                "seatfortyfour",
                "seatfourtyfive",
                "seatfortysix",
                "seatfourtyseven");


        return "&"+seats.get(seatNumber-1)+"=1";
    }
}
