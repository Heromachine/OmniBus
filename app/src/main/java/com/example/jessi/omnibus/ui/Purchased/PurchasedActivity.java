package com.example.jessi.omnibus.ui.purchased;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.ui.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurchasedActivity extends AppCompatActivity {

    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.thankyou)
    TextView thankyou;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.btn_search)
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_exit, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                getApplication().onTerminate();
                break;
            case R.id.btn_search:
                Intent intent = new Intent(PurchasedActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
