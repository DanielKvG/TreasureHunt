package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RemainingTimeActivity extends AppCompatActivity {

    private TextView Tv_Timer3;
    private TextView Tv_remTime;
    private int lastPosition;
    boolean timeOn;
    private Button btn_found;
    private CountDownTimer remainCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining_time);

        Tv_Timer3 = findViewById(R.id.Tv_Timer3);
        Tv_remTime = findViewById(R.id.Tv_remTime);
        btn_found = findViewById(R.id.btn_found);

        SharedPreferences settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        timeOn = settings.getBoolean("ON", true);
        lastPosition = settings.getInt("POSITION", 0);


        if (timeOn) {
            Tv_remTime.setText("Remaining time");
            remainCountDown = new CountDownTimer(lastPosition*1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    Tv_Timer3.setText(millisUntilFinished / 1000 + " seconds to go!");
                }

                public void onFinish() {
                    startActivity(new Intent(RemainingTimeActivity.this, WinActivity.class));
                }
            }.start();
        } else {
            Tv_remTime.setText("");
            Tv_Timer3.setText("");
        }

    }

    public void btn_found(View view) {
        remainCountDown.cancel();
        startActivity(new Intent(RemainingTimeActivity.this, LoseActivity.class));
    }
}