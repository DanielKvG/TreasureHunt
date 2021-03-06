package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author DanielKvG
 * The user will arrive in this count down screen when he is not the first player to connect.
 * He must wait untill the first player starts the game
 */

public class LoadingScreenActivity extends AppCompatActivity {

    private TextView Tv_info;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Tv_info = findViewById(R.id.Tv_info);
        progressBar = findViewById(R.id.progressBar);

        //make a new handler for a count down activity, the action will start when the dalay has ended
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(LoadingScreenActivity.this, ChooseFunctionAction.class));
            }

        }, 5000L); //specify the delay in mili seconds here
    }
}