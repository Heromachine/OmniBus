package com.example.jessi.omnibus.ui.seat;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.MyViewHolder> {
    private static final String TAG = "SeatAdapter";

    private Context context;
    List<Vector<String>> seatList;
    SeatContract.Presenter presenter;
    SeatContract.View view;

    List<String> namesOfSeatSelected;

    private int numOfSeatsSelected = 0;

    public SeatAdapter(SeatsActivity context, List<Vector<String>> seatList) {
        Log.d(TAG, "SeatAdapter: " + seatList.toString());
        this.context = context;
        this.seatList = seatList;
        view = context;
        namesOfSeatSelected = new ArrayList<>();


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "SeatAdapter";

        ImageView ivSeat;
        TextView tvSeat;
        Context context;
        List<Vector<String>> seats;

        RelativeLayout rlSeat;
        SeatContract.View view;

        public MyViewHolder(@NonNull View itemView, Context context, SeatContract.View view, List<Vector<String>> seats) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: ");

            this.rlSeat = itemView.findViewById(R.id.rl_Seat);
            this.ivSeat = itemView.findViewById(R.id.iv_seat);
            this.tvSeat = itemView.findViewById(R.id.tv_seat);
            this.context = context;
            this.seats = seats;
            this.view = view;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_seat, viewGroup, false);
        SeatAdapter.MyViewHolder myViewHolder = new SeatAdapter.MyViewHolder(view, context, this.view, this.seatList);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int pos) {
        Log.d(TAG, "onBindViewHolder: ");

        String seatName = seatList.get(pos).get(0).replace("s", "");
        int seatNumber = Integer.valueOf(seatName);
        if (seatNumber%2 == 0 && seatNumber%4 == 2){

            Log.d(TAG, "onBindViewHolder: SeatNumber = " + seatNumber);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(0, 0, 35, 0);
            myViewHolder.rlSeat.setLayoutParams(layoutParams);
        }

        myViewHolder.tvSeat.setText(seatList.get(pos).get(0));

        if(seatList.get(pos).get(1).contentEquals("1"))
        {
            myViewHolder.ivSeat.setBackgroundColor(Color.rgb(117, 117, 117 ));
            myViewHolder.tvSeat.setTextColor(Color.rgb(255, 255, 255));
        }
        else
        if(seatList.get(pos).get(1).contentEquals("0"))
        {
            myViewHolder.ivSeat.setBackgroundColor(Color.rgb(189, 189, 189 ));
            myViewHolder.tvSeat.setTextColor(Color.rgb(0, 0, 0));
        }
        else
        if(seatList.get(pos).get(1).contentEquals("2"))
        {
            myViewHolder.ivSeat.setBackgroundColor(Color.rgb(255, 235, 59 ));
            myViewHolder.tvSeat.setTextColor(Color.rgb(255, 255, 255));
        }
        myViewHolder.view.setNameOfSeatSelected(namesOfSeatSelected);
        myViewHolder.ivSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seatList.get(pos).get(1).contentEquals("0"))
                {
                    if (numOfSeatsSelected < 5) {
                        seatList.get(pos).set(1, "2");
                        myViewHolder.ivSeat.setBackgroundColor(Color.rgb(255, 235, 59));
                        myViewHolder.tvSeat.setTextColor(Color.rgb(0, 0, 0));
                        ++numOfSeatsSelected;
                        namesOfSeatSelected.add(seatList.get(pos).get(0));
                        myViewHolder.view.setNameOfSeatSelected(namesOfSeatSelected);

                    }
                    else {
                        Toast.makeText(context, "Reached Limit", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (seatList.get(pos).get(1).contentEquals("1"))
                {
                    Toast.makeText(context, "Unavailable", Toast.LENGTH_SHORT).show();
                }
                else if(seatList.get(pos).get(1).contentEquals("2"))
                {
                    seatList.get(pos).set(1, "0");
                    myViewHolder.ivSeat.setBackgroundColor(Color.rgb(189, 189, 189 ));
                    myViewHolder.tvSeat.setTextColor(Color.rgb(0, 0, 0));

                    for(int i = 0; i < namesOfSeatSelected.size(); i++){
                        if (namesOfSeatSelected.get(i).contentEquals(seatList.get(pos).get(0))){
                            namesOfSeatSelected.remove(i);
                        }
                    }
                    myViewHolder.view.setNameOfSeatSelected(namesOfSeatSelected);
                    --numOfSeatsSelected;

                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }
}
