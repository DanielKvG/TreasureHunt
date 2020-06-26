package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * @author DanielKvG
 * Startupscreen when the app is started, information about the application is showed.
 */

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //create a new handler to delay the action
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(StartupActivity.this, MainActivity.class));
            }

        }, 11000L); //delay is set to 11 seconds to be able to read the full text, in the mass product app this will be different.
    }
}