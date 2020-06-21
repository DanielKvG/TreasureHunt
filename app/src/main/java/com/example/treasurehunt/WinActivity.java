package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }

    public void button2(View view) {
        startActivity(new Intent(WinActivity.this, GameModes.class));
    }

    public void button(View view) {
        startActivity(new Intent(WinActivity.this, AdminPopup.class));
    }
}