package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * @author DanielKvG
 * this is the countdown activity for the seekers
 */

public class CountDownActivity extends AppCompatActivity {

    private TextView Tv_Timer4;
    private int lastHidePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        Tv_Timer4 = findViewById(R.id.Tv_Timer4);

        //use the shared preferences to access the time defined time or use the default value
        SharedPreferences settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        lastHidePosition = settings.getInt("HIDE_POSITION", 30);

        //make a new countdowntimer, when the time got from the shared preferences over is, start the new activity
        new CountDownTimer(lastHidePosition*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                //show the remaining time
                Tv_Timer4.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish() {
                //start activity when countdown finished
                startActivity(new Intent(CountDownActivity.this, MapsActivity.class));
            }
        }.start();

    }


}