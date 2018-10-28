package com.example.jessi.omnibus.ui.seat;

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
import com.example.jessi.omnibus.data.models.SeatInfoRequest;
import com.example.jessi.omnibus.data.models.SeatReservationRequest;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        namesOfSeatSelected = new ArrayList<>();
        seatCount = findViewById(R.id.tv_seat_no);

        ButterKnife.bind(this);
        Bundle extras2 = getIntent().getExtras();
        if (extras2 != null) {
            seatInfoRequest = new SeatInfoRequest(extras2.getString("BusID"));
            busID = extras2.getString("BusID");
            Log.d(TAG, "onCreate: Seat Bundle Good");
        }
        else {
            Toast.makeText(this, "EMPTY BUNDLE", Toast.LENGTH_SHORT).show();
            seatInfoRequest = new SeatInfoRequest("102");
            busID = "102";
        }

        presenter = new SeatPresenter(this);
    }

    @OnClick(R.id.btn_seat)
    public void onViewClicked(View view) {
        seatReservationRequest = new SeatReservationRequest(busID, namesOfSeatSelected);


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
