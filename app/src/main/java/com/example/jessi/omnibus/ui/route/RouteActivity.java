package com.example.jessi.omnibus.ui.route;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.RouteRequest;
import com.example.jessi.omnibus.data.models.Routes;
import com.example.jessi.omnibus.ui.busselection.BusActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RouteActivity extends AppCompatActivity implements RouteContract.View{
    private static final String TAG = "RouteActivity";
    RouteContract.Presenter presenter;

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    List<String> routeNameList;
    Routes routes;
    RouteRequest routeRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        initRouteRequest();
    }

    private void initRouteRequest(){
        List<String> coordinates = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            coordinates.add(extras.getString("StartLat"));
            coordinates.add(extras.getString("StartLon"));
            coordinates.add(extras.getString("EndLat"));
            coordinates.add(extras.getString("EndLon"));
        }

        routeRequest= new RouteRequest(
                coordinates.get(0),
                coordinates.get(1),
                coordinates.get(2),
                coordinates.get(3));

        presenter = new RoutePresenter(this);
    }

    @Override
    public void initListView(final Routes routes){

        Log.d(TAG, "initListView: Routes size: " + routes.getRoute().size());
        routeNameList = new ArrayList<>();
        for(int i = 0; i < routes.getRoute().size(); i++){
            routeNameList.add(routes.getRoute().get(i).getRoutename());
        }
        this.routes = routes;
        arrayAdapter = new ArrayAdapter<String>
                (
                        RouteActivity.this,
                        R.layout.item_route,
                        R.id.tv_route_name,
                        routeNameList
                );

        listView = findViewById(R.id.lv_route);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent busIntent = new Intent(RouteActivity.this, BusActivity.class);
                busIntent.putExtra("RouteID", routes.getRoute().get(i).getId());
                startActivity(busIntent);
            }
        });
    }

    @Override
    public RouteRequest getRouteRequest(){
        return this.routeRequest;
    }
}
