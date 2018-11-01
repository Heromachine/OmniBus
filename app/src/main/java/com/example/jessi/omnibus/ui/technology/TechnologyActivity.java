package com.example.jessi.omnibus.ui.technology;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.ui.login.LogInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TechnologyActivity extends AppCompatActivity {

    @BindView(R.id.tv_techs)
    TextView tvTechs;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        ButterKnife.bind(this);
        tvTechs.setText("Retrofit\n" +
                "Recycler iView\n" +
                "CardView\n" +
                "Picasso\n" +
                "Butternife\n" +
                "Calender\n" +
                "Simplify\n" +
                "Animation\n" +
                "RxValidator\n" +
                "Singleton\n" +
                "SharedPreferences\n" +
                "JUnitTesting\n" +
                "Espresso\n");
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent = new Intent(TechnologyActivity.this, LogInActivity.class);
        startActivity(intent);
    }
}
