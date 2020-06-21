package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
    }

    public void btn_TreasureHuntPlay(View view) {
        startActivity(new Intent(LoseActivity.this, AdminPopup.class));
    }

    public void btn_PlayOtherMode(View view) {
        startActivity(new Intent(LoseActivity.this, GameModes.class));
    }
}