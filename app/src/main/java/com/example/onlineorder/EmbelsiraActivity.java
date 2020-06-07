package com.example.onlineorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EmbelsiraActivity extends AppCompatActivity {
    public ImageView clickOnMenu;
    public ImageView plus;
    public ImageView minus;
    int number_p1=0;
    int number_p2=0;
    int number_p3=0;
    int number_p4=0;
    public TextView edit_count_p1;
    public TextView edit_count_p2;
    public TextView edit_count_p3;
    public TextView edit_count_p4;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embelsira);
        clickOnMenu = (ImageView) findViewById(R.id.button_menu_2);
        clickOnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MenuActivity.class));
                //overridePendingTransition(0,0);

            }
        });
        plus = (ImageView) findViewById(R.id.plus_p1);

        edit_count_p1 = (TextView) findViewById(R.id.edit_count_p1);

        plus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                edit_count_p1.setText("" + ++number_p1);


            }

        });
        minus = (ImageView) findViewById(R.id.minus_p1);

        edit_count_p1 = (TextView) findViewById(R.id.edit_count_p1);

        minus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                if(number_p1>0) {
                    edit_count_p1.setText("" + --number_p1);
                }

            }

        });

        //---------------------------------------------------------------------

        plus = (ImageView) findViewById(R.id.plus_p2);

        edit_count_p2 = (TextView) findViewById(R.id.edit_count_p2);

        plus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                edit_count_p2.setText("" + ++number_p2);


            }

        });
        minus = (ImageView) findViewById(R.id.minus_p2);

        edit_count_p2 = (TextView) findViewById(R.id.edit_count_p2);

        minus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                if(number_p2>0) {
                    edit_count_p2.setText("" + --number_p2);
                }

            }

        });

        //---------------------------------------------------------------------

        plus = (ImageView) findViewById(R.id.plus_p3);

        edit_count_p3 = (TextView) findViewById(R.id.edit_count_p3);

        plus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                edit_count_p3.setText("" + ++number_p3);


            }

        });
        minus = (ImageView) findViewById(R.id.minus_p3);

        edit_count_p3 = (TextView) findViewById(R.id.edit_count_p3);

        minus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                if(number_p3>0) {
                    edit_count_p3.setText("" + --number_p3);
                }

            }

        });

        //---------------------------------------------------------------------

        plus = (ImageView) findViewById(R.id.plus_p4);

        edit_count_p4 = (TextView) findViewById(R.id.edit_count_p4);

        plus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                edit_count_p4.setText("" + ++number_p4);


            }

        });
        minus = (ImageView) findViewById(R.id.minus_p4);

        edit_count_p4 = (TextView) findViewById(R.id.edit_count_p4);

        minus.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                if(number_p4>0) {
                    edit_count_p4.setText("" + --number_p4);
                }

            }

        });
//toolbar gradient
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }





    public void goBack(View view){

        super.onBackPressed();
        overridePendingTransition(0,0);



    }


}
