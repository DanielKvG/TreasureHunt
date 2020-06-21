package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsPopupActivity extends AppCompatActivity {

    TextView Tv_NumbHide;
    TextView Tv_Time;
    TextView Tv_HideTime;
    private int time;
    private int NumbHide;
    private int hidetime;
    private SeekBar SbTime;
    private SeekBar SbHide;
    private SeekBar SbHideTime;
    boolean timeOn;
    int lastPosition;
    int lastHidePosition;
    SharedPreferences settings;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_popup);

        ImageButton btn_Return = findViewById(R.id.btn_Return);

        //initialize seekbar for number of seekers
        SbHide = findViewById(R.id.SbHide);
        SbHide.setOnSeekBarChangeListener(SbSeekChangeListener);

        //initialize textview for displaying number of hiders
        NumbHide = SbHide.getProgress();
        Tv_NumbHide = findViewById(R.id.Tv_NumbHide);
        Tv_NumbHide.setText("Number of hiders: " + NumbHide);

        //initialize seekbar for time
        SbTime = findViewById(R.id.SbTime);
        //disable seekbar
        SbTime.setEnabled(false);

        //initialize textview for displaying time
        time = SbTime.getProgress();
        Tv_Time = findViewById(R.id.Tv_Time);
        Tv_Time.setText("Time to search: "+ lastPosition + " seconds");

        //shared preferneces
        settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        timeOn = settings.getBoolean("ON", true);
        lastPosition = settings.getInt("POSITION", 30);
        SbTime.setProgress(lastPosition);
        Tv_Time.setText("Time to search: "+ lastPosition + " seconds");

        SbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                Tv_Time.setText("Time to search: "+ lastPosition + " seconds");
                saveSettingsInt("POSITION", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //seekbar for hide time
        Tv_HideTime = findViewById(R.id.Tv_HideTime);
        SbHideTime = findViewById(R.id.sbHideTime);
        lastHidePosition = settings.getInt("HIDE_POSITION", 30);
        SbHideTime.setProgress(lastHidePosition);
        Tv_HideTime.setText("Time to hide: " + lastHidePosition + " seconds");
        hidetime = SbHideTime.getProgress();

        SbHideTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                Tv_HideTime.setText("Time to hide: " + hidetime + " seconds");
                saveSettingsInt("HIDE_POSITION", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //initialize switch for time
        Switch STime = (Switch) findViewById(R.id.STime);
        //create oncheckedchangelistener
        STime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SbTime.setEnabled(true);
                    Tv_Time.setEnabled(true);
                    saveSettingsBoolean("ON", timeOn);
                }
                else {
                    SbTime.setEnabled(false);
                    Tv_Time.setEnabled(false);
                    saveSettingsBoolean("ON", timeOn);
                }
            }
        });

        btn_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numbHide = SbHide.getProgress();
                Intent intent = new Intent(SettingsPopupActivity.this, AddNames.class);
                intent.putExtra("NumbHide_key", numbHide);
                startActivity(intent);
            }
        });

    }

    public void saveSettingsBoolean(String s, boolean b) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(s, b);
        editor.apply();
    }

    public void saveSettingsInt(String s, int i) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(s, i);
        editor.apply();
    }


    SeekBar.OnSeekBarChangeListener SbSeekChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar SbHide, int NumbHide, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Tv_NumbHide.setText("Number of hiders: " + NumbHide);
        }

        @Override
        public void onStartTrackingTouch(SeekBar SbHide) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar SbHide) {

        }

    };

}