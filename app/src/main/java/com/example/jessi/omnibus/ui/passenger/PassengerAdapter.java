package com.example.jessi.omnibus.ui.passenger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.Passenger;
import com.example.jessi.omnibus.util.AppController;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.MyViewHolder> {

    private static final String TAG = "PassengerAdapter";

    private Context context;
    List<Passenger> passengerList;
    char gender = 'M';
    boolean isValid = false;

    public PassengerAdapter(Context context, List<Passenger> passengerList) {
        this.context = context;
        this.passengerList = passengerList;
        Log.d(TAG, "PassengerAdapter: Passenger List Size = "+ passengerList.size());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPassengerCount;
        EditText etFirstName;
        EditText etLastName;
        EditText etAge;
        RadioButton rbMale;
        RadioButton rbFemal;
        Context context;
        List<Passenger> passengerList;
        boolean isValid = false;



        public MyViewHolder(@NonNull View itemView, Context context, List<Passenger> passengerList) {
            super(itemView);

            for(int i = 0; i < passengerList.size();i ++)
            {
                passengerList.get(i).setLastName("");
                passengerList.get(i).setFirstName("");
                passengerList.get(i).setAge("");
                passengerList.get(i).setGender("");
            }
            this.context = context;
            this.passengerList = passengerList;
            this.etFirstName = itemView.findViewById(R.id.et_passenger_fname);
            this.etLastName = itemView.findViewById(R.id.et_passenger_lname);
            this.etAge = itemView.findViewById(R.id.et_age);
            this.rbMale = itemView.findViewById(R.id.rb_male);
            this.rbFemal = itemView.findViewById(R.id.rb_female);
            this.tvPassengerCount = itemView.findViewById(R.id.tv_passenger_count);


            initRxValidator ();
        }

        public boolean isValid() {
            return isValid;
        }

        public void setValid(boolean valid) {
            isValid = valid;
        }

        private void initRxValidator ()
        {
            RxValidator.createFor(this.etAge)
                    .nonEmpty()
                    .digitOnly()
                    .maxLength(3, "Format: 3 #s")
                    .onFocusChanged()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<RxValidationResult<EditText>>() {
                        @Override public void call(RxValidationResult<EditText> etResult) {
                            etResult.getItem().setError(etResult.isProper() ? null : etResult.getMessage());

                            if(etResult.isProper())
                            {
                                setValid(true);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override public void call(Throwable throwable) {
                            Log.e(TAG, "Validation error", throwable);
                        }
                    });

            RxValidator.createFor(this.etFirstName)
                    .nonEmpty()
                    .minLength(2, "Length 2+")
                    .maxLength(15)
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
                                setValid(true);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {

                        }
                    });

            RxValidator.createFor(this.etLastName)
                    .nonEmpty()
                    .minLength(2, "Length 2+")
                    .maxLength(15)
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
                                setValid(true);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {

                        }
                    });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_passenger, viewGroup, false);
        PassengerAdapter.MyViewHolder myViewHolder = new PassengerAdapter.MyViewHolder(view, context, this.passengerList);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int pos) {

        myViewHolder.tvPassengerCount.setText("Passenger "+ (pos +1)+ ":"+ passengerList.get(pos).getSeat());
        myViewHolder.rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.rbFemal.setActivated(false);
                passengerList.get(pos).setGender("M");
            }
        });
        myViewHolder.rbFemal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.rbMale.setActivated(false);
                passengerList.get(pos).setGender("F");
            }
        });

        passengerList.get(pos).setFirstName(myViewHolder.etFirstName.getText().toString());
        passengerList.get(pos).setLastName(myViewHolder.etLastName.getText().toString());
        passengerList.get(pos).setAge(myViewHolder.etAge.getText().toString());
        this.setValid(myViewHolder.isValid());
    }

    @Override
    public int getItemCount() {
        return passengerList.size();
    }

    public  List<Passenger> getPassengerList()
    {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

}
