package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class ExitActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_activty);

        //initialize display metrics
        DisplayMetrics DM = new DisplayMetrics();

        //set position and dimensions popup screen
        getWindowManager().getDefaultDisplay().getMetrics(DM);

        int height = DM.heightPixels;
        int width = DM.widthPixels;

        getWindow().setLayout((int)(width/1.2), (int)(height/3.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);
    }

    public void btn_Rematch(View view) {
        startActivity(new Intent(ExitActivty.this, AdminPopup.class));
    }

    public void btn_Gamemode(View view) {
        startActivity(new Intent(ExitActivty.this, GameModes.class));
    }
}