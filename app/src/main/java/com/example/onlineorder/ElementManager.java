package com.example.onlineorder;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ElementManager extends AppCompatActivity {

    public static String productName = "";
    public static int sasia = 0;
    public static double cmimi = 0.0;


    public static void getElementData(LinearLayout element){


        ConstraintLayout Emri_Sasia = (ConstraintLayout) element.getChildAt(0);
        TextView emri_productit = (TextView) Emri_Sasia.getChildAt(0);
        //System.out.println("****   "+twme.getText().toString());
        productName = emri_productit.getText().toString();//Merr emrin e productit
        LinearLayout ln = (LinearLayout) Emri_Sasia.getChildAt(1);
        TextView  sasiaText= (TextView) ln.getChildAt(0);
        //System.out.println("****   "+number.getText().toString());
        sasia = Integer.parseInt(sasiaText.getText().toString());

        //*kap cmimin
        LinearLayout Cmim = (LinearLayout) element.getChildAt(1);
        LinearLayout cmimLabel = (LinearLayout) Cmim.getChildAt(1);
        TextView cmimtext = (TextView) cmimLabel.getChildAt(0);
        cmimi = Double.parseDouble(String.valueOf(cmimtext.getText().toString().substring(1)));
        //System.out.println("****   "+cmimtext.getText().toString());


    }

}
