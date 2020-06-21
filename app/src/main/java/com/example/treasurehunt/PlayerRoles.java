package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerRoles extends AppCompatActivity {

    private TextView TvSeekers;
    private TextView TvHiders;
    private ListView LVSeekers;
    private ListView LVHiders;
    private Handler HideHandler;
    private Handler SeekHandler;

    private TextView Tv_wait;
    private View V_Wait;
    private ProgressBar Pb_wait;
    private View V_Seek;
    private View V_Hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roles);

        LVSeekers = findViewById(R.id.LVSeekers);
        LVHiders = findViewById(R.id.LVHiders);
        Tv_wait = findViewById(R.id.tv_wait);
        V_Hide = findViewById(R.id.v_Hide);
        V_Seek = findViewById(R.id.v_Seek);
        V_Wait = findViewById(R.id.v_wait);
        Pb_wait = findViewById(R.id.pb_wait);

        String seekers = getIntent().getStringExtra("seeker_key");
        List<String> SeekerList = new ArrayList<String>(Arrays.asList(seekers));

        String hiders = getIntent().getStringExtra("hider_key");
        List<String> HiderList = new ArrayList<String>(Arrays.asList(hiders));

        ArrayAdapter<String> SeekerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SeekerList);
        ArrayAdapter<String> HiderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, HiderList);

        LVSeekers.setAdapter(SeekerAdapter);
        LVHiders.setAdapter(HiderAdapter);

        //set start visibility for following fields
        V_Seek.setVisibility(View.INVISIBLE);
        V_Hide.setVisibility(View.INVISIBLE);
        V_Wait.setVisibility(View.INVISIBLE);
        Pb_wait.setVisibility(View.INVISIBLE);
        Tv_wait.setVisibility(View.INVISIBLE);

    }


    //method for seeker
    public void imageView5(View view) {

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
                startActivity(new Intent(PlayerRoles.this, CountDownActivity.class));
            }

        }, 6000L);

    }

    //method for hider
    public void imageView6(View view) {

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
                startActivity(new Intent(PlayerRoles.this, CountDownActivityHide.class));
            }

        }, 6000L);
    }
}