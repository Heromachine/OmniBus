package com.example.jessi.omnibus.ui.splash;

import android.content.Intent;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jessi.omnibus.R;
import com.example.jessi.omnibus.ui.login.LogInActivity;
import com.example.jessi.omnibus.ui.technology.TechnologyActivity;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final View img = findViewById(R.id.ivombi);


        final SpringAnimation springAnim
                = new SpringAnimation(
                img,
                DynamicAnimation.TRANSLATION_Y,
                0
        );
        springAnim.setStartValue(-1000f);
        springAnim.setMaxValue(4000);
        springAnim.setStartVelocity(.00001f);

        springAnim.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
        springAnim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);

        springAnim.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {

            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float value,
                                          float velocity) {
                springAnim.animateToFinalPosition(0);
            }
        });

        springAnim.start();

        intent = new Intent(this, TechnologyActivity.class);

        splashScreen(3000);

    }
    public void splashScreen(final int x) {
        Thread thread = new Thread() {
            //TODO Give run method

            @Override
            public void run() {
                super.run();
                try {
                    sleep(x);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

}
