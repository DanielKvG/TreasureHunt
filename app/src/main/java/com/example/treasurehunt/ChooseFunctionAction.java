package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author DanielKvG
 * In this action you can choose whether you are the seeker or the hider, if not the first player.
 */

public class ChooseFunctionAction extends AppCompatActivity {

    private TextView Tv_Function;
    private TextView Tv_wait;
    private TextView Tv_Seeker;
    private TextView Tv_Hider;
    private View V_Hide;
    private View V_Seek;
    private View V_Wait;
    private ImageView Iv_Seek;
    private ImageView Iv_Hide;
    private ProgressBar Pb_wait;
    private Handler SeekHandler;
    private Handler HideHandler;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_function_action);

        //initialize fields
        Tv_Function = findViewById(R.id.Tv_Function);
        Tv_wait = findViewById(R.id.Tv_wait);
        Tv_Seeker = findViewById(R.id.Tv_Seeker);
        Tv_Hider = findViewById(R.id.Tv_Hider);
        V_Hide = findViewById(R.id.V_Hide);
        V_Wait = findViewById(R.id.V_wait);
        V_Seek = findViewById(R.id.V_Seek);
        Iv_Hide = findViewById(R.id.Iv_Hider);
        Iv_Seek = findViewById(R.id.Iv_Seeker);
        Pb_wait = findViewById(R.id.Pb_wait);

        //set start visibility for following fields
        V_Seek.setVisibility(View.INVISIBLE);
        V_Hide.setVisibility(View.INVISIBLE);
        V_Wait.setVisibility(View.INVISIBLE);
        Pb_wait.setVisibility(View.INVISIBLE);
        Tv_wait.setVisibility(View.INVISIBLE);

        //provide default values
        settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("POSITION", 30);
        editor.putInt("HIDE_POSITION", 30);
        editor.putBoolean("ON", true);


    }

    public void Iv_Seeker(View view) {

        //change visibilities of following fields
        V_Seek.setVisibility(view.VISIBLE);
        V_Hide.setVisibility(view.INVISIBLE);
        V_Wait.setVisibility(view.VISIBLE);
        Tv_wait.setVisibility(view.VISIBLE);
        Pb_wait.setVisibility(view.VISIBLE);


        //place event handler activating next activity when everyone is ready (in the application after 6 seconds)
        SeekHandler = new Handler();
        SeekHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(ChooseFunctionAction.this, CountDownActivity.class));
            }

        }, 6000L);
    }

    public void Iv_Hider(View view) {

        //change visibilities of following fields
        V_Seek.setVisibility(view.INVISIBLE);
        V_Hide.setVisibility(view.VISIBLE);
        V_Wait.setVisibility(view.VISIBLE);
        Tv_wait.setVisibility(view.VISIBLE);
        Pb_wait.setVisibility(view.VISIBLE);

        //place event handler activating next activity when everyone is ready (in the application after 6 seconds)
        HideHandler = new Handler();
        HideHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(ChooseFunctionAction.this, CountDownActivityHide.class));
            }

        }, 6000L);
    }

}