package com.example.jessi.omnibus.ui.seat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.Coupons;
import com.example.jessi.omnibus.data.models.SeatInfoRequest;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;
import com.example.jessi.omnibus.ui.chekcout.CheckOutActivity;
import com.example.jessi.omnibus.ui.coupon.CouponActivity;
import com.example.jessi.omnibus.ui.passenger.PassengerActivity;
import com.example.jessi.omnibus.util.AppController;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeatsActivity extends AppCompatActivity implements SeatContract.View {
    private static final String TAG = "SeatsActivity";

    SeatContract.Presenter presenter;
    @BindView(R.id.rv_seats)
    RecyclerView recyclerView;
    @BindView(R.id.btn_seat)
    Button btnSeat;
    TextView seatCount;

    SeatInfoRequest seatInfoRequest;
    List<String> namesOfSeatSelected;
    SeatReservationRequest seatReservationRequest;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String busID;
    private Intent passengerIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        ButterKnife.bind(this);
        namesOfSeatSelected = new ArrayList<>();
        seatCount = findViewById(R.id.tv_seat_no);

        Log.d(TAG, "onCreate: BusID = "+   AppController.getInstance().getSP(
                this,
                "TABLE",
                "BusID"));

        seatInfoRequest
                = new SeatInfoRequest(
                AppController.getInstance().getSP(
                        this,
                        "TABLE",
                        "BusID"));


        presenter = new SeatPresenter(this);
    }

    @OnClick(R.id.btn_seat)
    public void onViewClicked(View view) {

        if(namesOfSeatSelected.size() != 0){
            seatReservationRequest = new SeatReservationRequest(busID, namesOfSeatSelected);

            for(int i = 0; i < namesOfSeatSelected.size(); i++)
            {
                AppController.getInstance().addSP(
                        SeatsActivity.this,
                        "TABLE", ("SeatName_"+i),
                        namesOfSeatSelected.get(i));
            }

            AppController.getInstance().addSP(
                            SeatsActivity.this,
                            "TABLE",
                            "SeatCount",
                            String.valueOf(namesOfSeatSelected.size()));

            passengerIntent = new Intent(SeatsActivity.this, PassengerActivity.class);
            startActivity(passengerIntent);
        }
        else
        {
            Toast.makeText(this, "No Seats Selected", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "onViewClicked: SEAT: Names of Seats Size = "+namesOfSeatSelected.size());
    }

    @Override
    public void initRecyclerView(List<Vector<String>> seatInfoList){

        layoutManager = new GridLayoutManager(SeatsActivity.this, 4);
        adapter = new SeatAdapter(SeatsActivity.this, seatInfoList);
        recyclerView = findViewById(R.id.rv_seats);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public SeatInfoRequest getSeatInfoRequest() {
        return seatInfoRequest;
    }

    @Override
    public List<String> getNameOfSeatSelected() {
        return namesOfSeatSelected;
    }

    @Override
    public void setNameOfSeatSelected(List<String> names) {
        this.namesOfSeatSelected = names;
    }

    @Override
    public void removeSeatSelected(String name) {

    }
}
