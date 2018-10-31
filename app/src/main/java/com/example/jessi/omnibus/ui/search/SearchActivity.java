package com.example.jessi.omnibus.ui.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.CityNamesModel;
import com.example.jessi.omnibus.data.models.CitiesModel;
import com.example.jessi.omnibus.util.AppController;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchContract.View {

    SearchContract.Presenter presenter;
    CitiesModel citiesModel;
    List<String> cityNameList;
    EditText etCityDeparture;
    EditText etCityArrival;
    TextView tvSelectDate;
    Button btnSearch;
    String dateSelected;
    boolean bDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //TODO ADD FUNCTIONS
        presenter = new SearchPresenter(this);
        etCityDeparture = findViewById(R.id.et_city_departure);
        etCityArrival = findViewById(R.id.et_city_arrival);
        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bDate != true) {
                    Toast.makeText(SearchActivity.this, "Please Enter Date", Toast.LENGTH_SHORT).show();

                }else {
                    if (etCityArrival.getText().toString() != "" && etCityDeparture.getText().toString() != "") {
                        presenter.onButtonClicked(view);
                        presenter.setDateSelected(dateSelected);
                    } else {
                        Toast.makeText(SearchActivity.this, "Please Enter Both City Names", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        this.tvSelectDate = findViewById(R.id.tv_select_date);
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                Date today = c.getTime();
                SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
                String dateString = format.format(dateClicked);
                dateClicked = null;

                try {
                    dateClicked = format.parse( dateString );
                    dateSelected = dateString;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(!dateClicked.before(today)&& dateClicked.after(today) )
                {
                    tvSelectDate.setText(
                            dateString);
                    AppController.getInstance().addSP(SearchActivity.this, "TABLE", "Date", dateSelected);
                    bDate = true;
                }
                else
                {
                    //Toast.makeText(SearchActivity.this, "DateSelected: "+ dateClicked, Toast.LENGTH_SHORT).show();
                    Toast.makeText(SearchActivity.this, "Past Date Invalid", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setCitiesModel(CitiesModel citiesModel) {
        this.citiesModel = citiesModel;
    }

    @Override
    public CitiesModel getCitiesModel() {
        return this.citiesModel;
    }

    @Override
    public CityNamesModel getCityNames() {
        CityNamesModel cityNamesModel = new CityNamesModel(etCityDeparture.getText().toString(), etCityArrival.getText().toString());
        return cityNamesModel;
    }
}
