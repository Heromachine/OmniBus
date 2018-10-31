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
import com.example.jessi.omnibus.ui.chekcout.CheckOutActivity;
import com.example.jessi.omnibus.util.AppController;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RouteActivity extends AppCompatActivity implements RouteContract.View{
    private static final String TAG = "RouteActivity";
    private RouteContract.Presenter presenter;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> routeNameList;
    private Routes routes;
    private RouteRequest routeRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        initRouteRequest();
    }

    private void initRouteRequest(){

        routeRequest= new RouteRequest(
                AppController.getInstance().getSP(this, "TABLE", "StartLat"),
                AppController.getInstance().getSP(this, "TABLE", "StartLon"),
                AppController.getInstance().getSP(this, "TABLE", "EndLat"),
                AppController.getInstance().getSP(this, "TABLE", "EndLon"));



        presenter = new RoutePresenter(this);
    }

    @Override
    public void initListView(final Routes routes){

        Log.d(TAG, "initListView: Routes = "+routes.toString());
        if(routes.getRoute() == null)
        {
            Toast.makeText(this, "No Routes for those locations", Toast.LENGTH_SHORT).show();

        }
        else
        {
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

                    AppController.getInstance().addSP(
                            RouteActivity.this,
                            "TABLE",
                            "RouteName",
                            routes.getRoute().get(i).getRoutename() );

                    AppController.getInstance().addSP(
                            RouteActivity.this,
                            "TABLE",
                            "RouteID",
                            routes.getRoute().get(i).getId());


                    Intent busIntent = new Intent(RouteActivity.this, BusActivity.class);
                    startActivity(busIntent);

                }
            });

        }



    }

    @Override
    public RouteRequest getRouteRequest(){
        return this.routeRequest;
    }
}
