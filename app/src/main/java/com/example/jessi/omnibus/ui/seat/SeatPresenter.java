package com.example.jessi.omnibus.ui.seat;

import android.content.Context;
import android.util.Log;

import com.example.jessi.omnibus.data.DataManager;
import com.example.jessi.omnibus.data.IDataManger;
import com.example.jessi.omnibus.data.models.SeatInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SeatPresenter implements SeatContract.Presenter, IDataManger.SeatInfoOnResponseListener {

    private static final String TAG = "SeatPresenter";
    SeatContract.View view;
    Context context;
    IDataManger dataManger;


    public SeatPresenter(SeatsActivity seatActivity) {
        view = seatActivity;
        context = seatActivity;
        dataManger = new DataManager();
        dataManger.networkRetrofitCallSeats(this, view.getSeatInfoRequest());
    }

    @Override
    public void getSeatsInfoModel(SeatInfo seatInfo) {

        view.initRecyclerView(prepareSeats(seatInfo));
    }

    private List<Vector<String>> prepareSeats(SeatInfo seatInfo){
        Log.d(TAG, "prepareSeats: ");
        List<Vector<String>> seats = new ArrayList<>();
        String seatinfoString = seatInfo.toString();
        int numberOfSeats = Integer.valueOf(seatInfo.getSeatinformation().get(0).getId());
        Log.d(TAG, "\n\nprepareSeats: " +seatinfoString + "| \n\nNumber of ID = " + numberOfSeats);
        int seatnumber=0;
        for (int i = 0; i < seatinfoString.length(); i++){

            if(seatinfoString.charAt(i) == '}')
                break;
            else
            if (seatinfoString.charAt(i) == '='){
                if(seatinfoString.charAt(i + 5)== ','){
                    ++seatnumber;
                    Vector<String> temp = new Vector<>();
                    temp.add("s"+ seatnumber);
                    temp.add(seatinfoString.charAt(i+3) + "");
                    seats.add(temp);

                }
            }
        }
        return seats;
    }
}
