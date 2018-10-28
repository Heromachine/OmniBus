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
import com.example.jessi.omnibus.ui.seat.SeatsActivity;

import java.util.ArrayList;
import java.util.List;

public class BusActivity extends AppCompatActivity implements BusContract.View{

    private static final String TAG = "BusActivity";
    private BusContract.Presenter presenter;
    private BusInfoRequest busInfoRequest;
    private BusInfoModel busInfoModel;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        Bundle extras2 = getIntent().getExtras();

        if (extras2 != null) {
            busInfoRequest = new BusInfoRequest(extras2.getString("RouteID"));
            Log.d(TAG, "onCreate: ROUTE ID = " + extras2.getString("RouteID"));
        }
        else {
            Log.d(TAG, "onCreate: ROUTE ID IS EMPTY");
            busInfoRequest = new BusInfoRequest("2");
        }

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
           // String format1 = String.format("|Bus Type: %20s|", busInfoModel.getBusinformation().get(i).getBustype());
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
                Intent seatIntent = new Intent(BusActivity.this, SeatsActivity.class);
                seatIntent.putExtra("BusID", busInfoModel.getBusinformation().get(i).getBusid());
                startActivity(seatIntent);

            }

        });
    }
}
