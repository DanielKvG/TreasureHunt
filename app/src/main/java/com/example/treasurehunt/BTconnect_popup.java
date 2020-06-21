package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BTconnect_popup extends Activity {
    private MainActivity mainActivity;
    ImageButton btnBTON;
    TextView askBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_tconnect_popup);
        //initialize button
        btnBTON = (ImageButton) findViewById(R.id.btnBTON);
        //initialize text
        askBT = (TextView) findViewById(R.id.askBT);

        btnBTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send request to enable bluetooth
                Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableBTIntent);
                //close the popup
                finish();
            }
        });



        //initialize display metrics
        DisplayMetrics DM = new DisplayMetrics();

        //set position and dimensions popup screen
        getWindowManager().getDefaultDisplay().getMetrics(DM);

        int height = DM.heightPixels;
        int width = DM.widthPixels;

        getWindow().setLayout((int)(width/1.2), (int)(height/3.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);



    }
}