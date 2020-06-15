package com.example.onlineorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MenuActivity extends AppCompatActivity {
    ConstraintLayout outside_menu, inside_menu;
    int y;
    int y_bottom;
    public ImageView info, profile, logout,quit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        outside_menu=(ConstraintLayout) findViewById(R.id.outside_menu);
        inside_menu=(ConstraintLayout) findViewById(R.id.inside_menu);
        info = (ImageView) findViewById(R.id.info);
        profile = (ImageView) findViewById(R.id.profile);
        logout = (ImageView) findViewById(R.id.logout);
        quit = (ImageView) findViewById(R.id.quit);


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), InfoActivity.class));

            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity((new Intent(Intent.ACTION_MAIN)).addCategory(Intent.CATEGORY_HOME).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                //moveTaskToBack(true);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.session.logoutUser();
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), ProfileActivity.class));

            }
        });


        outside_menu.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v,MotionEvent event) {

                y = (int)inside_menu.getY();

                y_bottom = y + (inside_menu).getHeight();
                if((int)event.getY() < y || (int)event.getY() > y_bottom)//&& (int)event.getY() < y_bottom
                {
                    onBackPressed();
                    overridePendingTransition(0,0);
                    System.out.println("--" + event.getY());

                }
                return  true;
            }
            ;



        });
//toolbar gradient
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }





}
