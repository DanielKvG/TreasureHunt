package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author DanielKvG
 * Activity where the user can choose the game mode, more gamemodes will be added in updates of the games
 */

public class GameModes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_modes);
    }

    //when chosen for the game mode treasure hunt, start the game mode
    public void btn_TreasureHunt(View view) {
        startActivity(new Intent(GameModes.this, AdminPopup.class));
    }
}