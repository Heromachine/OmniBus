package com.example.jessi.omnibus.ui.passenger;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.data.models.Passenger;
import com.github.phajduk.rxvalidator.RxValidationResult;
import com.github.phajduk.rxvalidator.RxValidator;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.MyViewHolder> {

    private static final String TAG = "PassengerAdapter";

    private Context context;
    private List<Passenger> passengerList;
    private char gender = 'M';
    private boolean isValid = false;
    private int seatConfirmed = 0;

    public PassengerAdapter(Context context, List<Passenger> passengerList) {
        this.context = context;
        this.passengerList = passengerList;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPassengerCount;
        EditText etFirstName;
        EditText etLastName;
        EditText etAge;
        RadioButton rbMale;
        RadioButton rbFemale;
        Context context;
        List<Passenger> passengerList;
        Button add;
        boolean isValid = false;

        public MyViewHolder(@NonNull View itemView, Context context, List<Passenger> passengerList) {
            super(itemView);

            this.context = context;
            this.passengerList = passengerList;
            this.etFirstName = itemView.findViewById(R.id.et_passenger_lname);
            this.etLastName = itemView.findViewById(R.id.et_passenger_fname);
            this.etAge = itemView.findViewById(R.id.et_age);
            this.rbMale = itemView.findViewById(R.id.rb_male);
            this.rbFemale = itemView.findViewById(R.id.rb_female);
            this.tvPassengerCount = itemView.findViewById(R.id.tv_passenger_count);
            this.add = itemView.findViewById(R.id.btn_add);


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
                myViewHolder.rbFemale.setChecked(false);
                passengerList.get(pos).setGender("M");
            }
        });
        myViewHolder.rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.rbMale.setChecked(false);
                passengerList.get(pos).setGender("F");
            }
        });


        this.setValid(myViewHolder.isValid());

        myViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(
                    myViewHolder.etFirstName.getText().length() == 0 ||
                    myViewHolder.etLastName.getText().length() == 0 ||
                    myViewHolder.etAge.getText().length() == 0)
                {
                    Toast.makeText(context, "Missing Info", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    passengerList.get(pos).setFirstName(myViewHolder.etFirstName.getText().toString());
                    passengerList.get(pos).setLastName(myViewHolder.etLastName.getText().toString());
                    passengerList.get(pos).setAge(myViewHolder.etAge.getText().toString());
                    myViewHolder.add.setBackgroundColor(Color.LTGRAY);
                    myViewHolder.etFirstName.setEnabled(false);
                    myViewHolder.etLastName.setEnabled(false);
                    myViewHolder.etAge.setEnabled(false);
                    myViewHolder.add.setEnabled(false);

                    seatConfirmed++;
                }
            }
        });


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

    public int getSeatConfirmed() {
        return seatConfirmed;
    }

    public void setSeatConfirmed(int seatConfirmed) {
        this.seatConfirmed = seatConfirmed;
    }
}
