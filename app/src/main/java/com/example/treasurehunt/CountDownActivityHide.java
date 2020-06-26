package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * @author DanielKvG
 * same acitivy as the countdown for seekers, but with other text
 */

public class CountDownActivityHide extends AppCompatActivity {

    private TextView Tv_Timer2;
    private int lastHidePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_hide);
        Tv_Timer2 = findViewById(R.id.Tv_Timer2);

        SharedPreferences settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        lastHidePosition = settings.getInt("HIDE_POSITION", 0);

        new CountDownTimer(lastHidePosition*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                Tv_Timer2.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish() {
                startActivity(new Intent(CountDownActivityHide.this, RemainingTimeActivity.class));
            }
        }.start();

    }

}