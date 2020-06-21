package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownActivity extends AppCompatActivity {

    private TextView Tv_Timer4;
    private int lastHidePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        Tv_Timer4 = findViewById(R.id.Tv_Timer4);

        SharedPreferences settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        lastHidePosition = settings.getInt("HIDE_POSITION", 0);

        new CountDownTimer(lastHidePosition*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                Tv_Timer4.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish() {
                startActivity(new Intent(CountDownActivity.this, MapsActivity.class));
            }
        }.start();

    }


}