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

/**
 * @author DanielKvG
 * in this activity you can change the number of hiders, hide time and search time
 */

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
    int lastNumbHide;
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

        //initialize seekbar for hide time
        SbHideTime = findViewById(R.id.sbHideTime);
        SbHideTime.setOnSeekBarChangeListener(SbHideTimeChangeListener);

        //initialize seekbar for seek time
        SbTime = findViewById(R.id.SbTime);
        SbTime.setOnSeekBarChangeListener(SbTimeChangeListener);

        //initialize textview for displaying number of hiders
        NumbHide = SbHide.getProgress();
        Tv_NumbHide = findViewById(R.id.Tv_NumbHide);
        Tv_NumbHide.setText("Number of hiders: " + NumbHide);

        //initialize textview for displaying hide time
        hidetime = SbHideTime.getProgress();
        Tv_HideTime = findViewById(R.id.Tv_HideTime);
        Tv_HideTime.setText("Time to hide: " + hidetime + " seconds");

        //initialize textview for displaying seek time
        time = SbTime.getProgress();
        Tv_Time = findViewById(R.id.Tv_Time);
        Tv_Time.setText("Time to search: "+ time + " seconds");

        //shared preferneces
        settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        timeOn = settings.getBoolean("ON", true);
        lastPosition = settings.getInt("POSITION", 30);
        lastNumbHide = settings.getInt("NumbHide", 2);
        lastHidePosition = settings.getInt("HIDE_POSITION", 30);

        //set the seekbars to their last position
        SbTime.setProgress(lastPosition);
        SbHide.setProgress(lastNumbHide);
        SbHideTime.setProgress(lastHidePosition);

        btn_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the progress of the hide seek bar to an integer
                int numbHide = SbHide.getProgress();
                Intent intent = new Intent(SettingsPopupActivity.this, AddNames.class);
                //add the number of hiders to the intent with the access key
                intent.putExtra("NumbHide_key", numbHide);
                startActivity(intent);
            }
        });

    }

    //method called when settings are changed, they will be stored in the saved preferences.
    public void saveSettingsInt(String s, int i) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(s, i);
        editor.apply();
    }

    //Detects change when the settings for number of hiders is changed
    SeekBar.OnSeekBarChangeListener SbSeekChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar SbHide, int i, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Tv_NumbHide.setText("Number of hiders: " + i);
            //call the save method
            saveSettingsInt("NumbHide", i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar SbHide) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar SbHide) {

        }

    };

    //Detects change when the settings for hide time are changed
    SeekBar.OnSeekBarChangeListener SbHideTimeChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Tv_HideTime.setText("Time to hide: " + i + " seconds");
            //call the save method
            saveSettingsInt("HIDE_POSITION", i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    //Detects change when the settings for hide time are changed
    SeekBar.OnSeekBarChangeListener SbTimeChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Tv_Time.setText("Time to search: "+ i + " seconds");
            //call the save method
            saveSettingsInt("POSITION", i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}