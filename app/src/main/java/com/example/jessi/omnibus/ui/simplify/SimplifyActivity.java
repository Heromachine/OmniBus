package com.example.jessi.omnibus.ui.simplify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jessi.omnibus.R;
import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

public class SimplifyActivity extends AppCompatActivity {
    private static final String TAG = "SimplifyActivity";
    private Simplify simplify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplify);
        simplify = new Simplify();
        simplify.setApiKey("sbpb_OWNmMWQ1YjUtNjMzNy00N2I3LTk2NjQtZDk4MmYzZTE1MWJh");
        final CardEditor cardEditor = (CardEditor) findViewById(R.id.card_editor_simplify);
        final Button checkoutButton = (Button) findViewById(R.id.btn_simplify);
        cardEditor.addOnStateChangedListener(new CardEditor.OnStateChangedListener() {
            @Override
            public void onStateChange(CardEditor cardEditor) {
                checkoutButton.setEnabled(cardEditor.isValid());
            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simplify.createCardToken(cardEditor.getCard(), new CardToken.Callback() {
                    @Override
                    public void onSuccess(CardToken cardToken) {
                        Toast.makeText(SimplifyActivity.this, "Payment Made", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d(TAG, "onError: " + throwable.getMessage());
                    }
                });
            }
        });

    }
}
