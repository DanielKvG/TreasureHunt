package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author DanielKvG
 * his activity will be showed when the user won the game.
 */

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }

    //when the exit button is clicked the exit popup will be showed.
    public void btn_Exit(View view) {
        startActivity(new Intent(WinActivity.this, ExitActivty.class));
    }
}