
package com.example.onlineorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

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

    public Button orderButton;

    public ArrayList<LinearLayout> CheckingElement = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embelsira);
        LinearLayout elementp1 = (LinearLayout) findViewById(R.id.elementp1);
        LinearLayout elementp2 = (LinearLayout) findViewById(R.id.elementp2);
        LinearLayout elementp3 = (LinearLayout) findViewById(R.id.elementp3);
        LinearLayout elementp4 = (LinearLayout) findViewById(R.id.elementp4);
        CheckingElement.add(elementp1);
        CheckingElement.add(elementp2);
        CheckingElement.add(elementp3);
        CheckingElement.add(elementp4);

        orderButton = (Button) findViewById(R.id.button5);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(int i = 0;i<CheckingElement.size();i++){
                    ElementManager.getElementData(CheckingElement.get(i));
                        if(ElementManager.sasia != 0)
                        {
                            String procutName = ElementManager.productName;
                            int sasia = ElementManager.sasia;
                            double cmimi = ElementManager.cmimi;

                            WriteOrder writeOrder = new WriteOrder(procutName,cmimi,sasia,SignUpActivity.OrderingUser);
                            writeOrder.execute();
                        }
                }

            }
        });

        clickOnMenu = (ImageView) findViewById(R.id.button_menu_2);
        clickOnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(view.getContext(), MenuActivity.class));
                //overridePendingTransition(0,0);
                startActivity(new Intent(view.getContext(), ShportaActivity.class));

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
