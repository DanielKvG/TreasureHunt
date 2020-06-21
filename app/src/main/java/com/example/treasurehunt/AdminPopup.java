package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class AdminPopup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_popup);

        //initialize display metrics
        DisplayMetrics DM2 = new DisplayMetrics();

        //set position and dimensions popup screen
        getWindowManager().getDefaultDisplay().getMetrics(DM2);

        int height = DM2.heightPixels;
        int width = DM2.widthPixels;

        getWindow().setLayout((int)(width/1.2), (int)(height/3.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);
    }

    public void btn_First(View view) {
        startActivity(new Intent(AdminPopup.this, AddNames.class));
    }

    public void btn_Other(View view) {
        startActivity(new Intent(AdminPopup.this, LoadingScreenActivity.class));
    }
}