package com.example.jessi.omnibus.ui.busselection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.BusInfoModel;
import com.example.jessi.omnibus.data.models.BusInfoRequest;
import com.example.jessi.omnibus.data.models.Coupons;
import com.example.jessi.omnibus.ui.chekcout.CheckOutActivity;
import com.example.jessi.omnibus.ui.coupon.CouponActivity;
import com.example.jessi.omnibus.ui.passenger.PassengerActivity;
import com.example.jessi.omnibus.ui.route.RouteActivity;
import com.example.jessi.omnibus.ui.seat.SeatsActivity;
import com.example.jessi.omnibus.util.AppController;

import java.util.ArrayList;
import java.util.List;

public class BusActivity extends AppCompatActivity implements BusContract.View{

    private static final String TAG = "BusActivity";
    private BusContract.Presenter presenter;
    private BusInfoRequest busInfoRequest;
    private BusInfoModel busInfoModel;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private Intent checkoutIntent;
    private Intent couponIntent;
    private Intent passengerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        passengerIntent = new Intent(BusActivity.this, PassengerActivity.class);
        busInfoRequest = new BusInfoRequest(
                AppController.getInstance().getSP(this, "TABLE", "RouteID"));

        presenter = new BusPresenter(this);
    }

    @Override
    public BusInfoRequest getBusInfoRequest(){
        return this.busInfoRequest;
    }

    @Override
    public void initListView(final BusInfoModel busInfoModel){
        this.busInfoModel = busInfoModel;
        List<String> busInfoList = new ArrayList<>();
        for(int i = 0; i < busInfoModel.getBusinformation().size(); i++)
        {
            String temp =
                    String.format("Bus Type:       %30s", busInfoModel.getBusinformation().get(i).getBustype())+
                    String.format("\nBus ID:         %30s", busInfoModel.getBusinformation().get(i).getBusid())+
                    String.format("\nBus Reg No:     %27s", busInfoModel.getBusinformation().get(i).getBusregistrationno())+
                    String.format("\nBoarding  Time:  %30s", busInfoModel.getBusinformation().get(i).getBoardingtime())+
                    String.format("\nDeparture Time: %30s", busInfoModel.getBusinformation().get(i).getBusdeparturetime())+
                    String.format("\nDropping  Time:  %30s", busInfoModel.getBusinformation().get(i).getDropingtime())+
                    String.format("\nDuration:        %30s", busInfoModel.getBusinformation().get(i).getJournyduration())+
                    String.format("\nFare:           %33s", busInfoModel.getBusinformation().get(i).getFare());
            busInfoList.add(temp);
        }
        arrayAdapter = new ArrayAdapter<String>
                (
                        BusActivity.this,
                        R.layout.item_bus,
                        R.id.tv_bus_info,
                        busInfoList
                );

        listView = findViewById(R.id.lv_businfo);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AppController.getInstance().addSP
                        (BusActivity.this,
                                "TABLE",
                                "BusType",
                                busInfoModel.getBusinformation().get(0).getBustype());

                AppController.getInstance().addSP
                        (BusActivity.this,
                                "TABLE",
                                "BusID",
                                busInfoModel.getBusinformation().get(0).getBusid());

                AppController.getInstance().addSP
                        (BusActivity.this,
                                "TABLE",
                                "BusRegNo",
                                busInfoModel.getBusinformation().get(0).getBusregistrationno());


                AppController.getInstance().addSP
                        (BusActivity.this,
                                "TABLE",
                                "BoardingTime",
                                busInfoModel.getBusinformation().get(0).getBoardingtime());
                AppController.getInstance().addSP
                        (BusActivity.this,
                                "TABLE",
                                "DroppingTime",
                                busInfoModel.getBusinformation().get(0).getDropingtime());
                AppController.getInstance().addSP
                        (BusActivity.this,
                                "TABLE",
                                "Duration",
                                busInfoModel.getBusinformation().get(0).getJournyduration());

                AppController.getInstance().addSP
                        (BusActivity.this,
                                "TABLE",
                                "Fare",
                                busInfoModel.getBusinformation().get(0).getFare());

                Intent seatIntent = new Intent(BusActivity.this, SeatsActivity.class);
                startActivity(seatIntent);

            }
        });
    }
}
