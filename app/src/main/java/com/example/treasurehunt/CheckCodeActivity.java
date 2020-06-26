package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author DanielKvG
 * in this acitivy the code found on the device will be checked
 * In the mass product application the code will be received through a string from the device.
 * Now the code is already defined (6249)
 */

public class CheckCodeActivity extends AppCompatActivity {

    private TextView Tv_Wrong;
    private EditText Et_Code;
    private Button btn_Check;
    private String EditCode;
    private int editCode;
    //define the code, on the place of the int will be the string received from the device.
    private int code=6249;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_code);

        Tv_Wrong = findViewById(R.id.Tv_Wrong);
        Et_Code = findViewById(R.id.Et_Code);
        btn_Check = findViewById(R.id.btn_Check);
        Tv_Wrong.setVisibility(View.INVISIBLE);

        //initialize display metrics
        DisplayMetrics DM2 = new DisplayMetrics();

        //set position and dimensions popup screen
        getWindowManager().getDefaultDisplay().getMetrics(DM2);

        int height = DM2.heightPixels;
        int width = DM2.widthPixels;

        getWindow().setLayout((int)(width/1.2), (int)(height/3.2));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);
    }


    public void btn_Check(View view) {

        //convert the edit text to a string
        EditCode = Et_Code.getText().toString();
        //convert the string to an integer
        editCode = Integer.parseInt(EditCode);

        //if the code filled in is the same as the defined code
        if (editCode == code) {
            startActivity(new Intent(CheckCodeActivity.this, WinActivity.class));
        } else { //else show the text "WRONG CODE"
            Tv_Wrong.setVisibility(View.VISIBLE);
        }
    }
}