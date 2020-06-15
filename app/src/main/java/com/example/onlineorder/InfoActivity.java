
package com.example.onlineorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }


    public void goBack(View view) {

        super.onBackPressed();
        overridePendingTransition(0, 0);


    }

    public void goHome(View view) {

        startActivity(new Intent(view.getContext(), MainActivity.class));
        overridePendingTransition(0, 0);


    }
    public void goShporta(View view) {

        startActivity(new Intent(view.getContext(), ShportaActivity.class));
        overridePendingTransition(0, 0);


    }
    public void goMenu(View view) {

        startActivity(new Intent(view.getContext(), MenuActivity.class));
        overridePendingTransition(0, 0);


    }
    public void goProfile(View view) {

        startActivity(new Intent(view.getContext(), ProfileActivity.class));
        overridePendingTransition(0, 0);


    }
}
