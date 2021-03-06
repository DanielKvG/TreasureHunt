package com.example.treasurehunt;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author DanielKvG
 * This is a predefined action from android studio involving the map. The found button and remaining
 * time are added.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView Tv_TimeRemain;
    private View V_Remain;
    private boolean timeOn;
    private int lastPosition;
    private CountDownTimer seekCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Tv_TimeRemain = findViewById(R.id.Tv_TimeRemain);
        V_Remain = findViewById(R.id.V_Remain);

        //get the remaining time from the shared preferences
        SharedPreferences settings = getSharedPreferences("PREFS", MODE_PRIVATE);
        timeOn = settings.getBoolean("ON", true);
        lastPosition = settings.getInt("POSITION", 0);

        //set the countdown to the defined time
        if (timeOn) {
            seekCountDown = new CountDownTimer(lastPosition*1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    Tv_TimeRemain.setText(millisUntilFinished / 1000 + " seconds remaining!");
                }

                public void onFinish() {
                    startActivity(new Intent(MapsActivity.this, LoseActivity.class));
                }
            }.start();
        } else { // if the seek count down is disabled, dont show it, this will be functional in the mass app.
            Tv_TimeRemain.setText("");
            V_Remain.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng enschede = new LatLng(52.241657, 6.846324);
        mMap.addMarker(new MarkerOptions().position(enschede).title("Marker in Enschede"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(enschede, 15));
    }

    public void V_Target(View view) {
        //cancel the countdown so the lose activity won't be activated
        seekCountDown.cancel();
        startActivity(new Intent(MapsActivity.this, CheckCodeActivity.class));
    }
}