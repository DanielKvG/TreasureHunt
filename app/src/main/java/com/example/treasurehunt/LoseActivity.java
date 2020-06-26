package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author DanielKvG
 * Show the user that he has lost
 */

public class LoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
    }

    //a popup is called to exit the current game
    public void btn_Exit2(View view) {
        startActivity(new Intent(LoseActivity.this, ExitActivty.class));
    }
}