package com.example.onlineorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.onlineorder.SessionManager.KEY_NAME;

public class ShportaActivity extends AppCompatActivity {

    public static String prd;
    public static ArrayList<TextView> write;
    public static LinearLayout ln,myln;
    public static LinearLayout bigcontainer;
    public static Context context;
    public Button checkout_button;
    public static TextView total_cmim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shporta);
        context = this;

        total_cmim = (TextView)findViewById(R.id.total_cmim);
        myln = (LinearLayout) findViewById(R.id.elementp1);
        bigcontainer = (LinearLayout) findViewById(R.id.scrollView2);


        checkout_button = (Button) findViewById(R.id.button5);

        final String actualUser = LoginActivity.session.getUserDetails().get(KEY_NAME).toString();
        System.out.println(actualUser);
        WriteOnShporta writeOnShporta = new WriteOnShporta(actualUser);
        writeOnShporta.execute();
        System.out.println("88sdfsfsdf " + prd);
        //write.setText(prd);
        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Checkout_reset checkout_reset = new Checkout_reset("cani");
                checkout_reset.execute();

                ShowMessage.alertView("Success", "CHECKOUT",context);
            }
        });

    }

    public static void write(ArrayList<String> productName,ArrayList<Integer> sasia,ArrayList<Double> cmimi,ArrayList<Double> total_,double total){

        System.out.println(productName.size() + "    sfgdfgsfsdg");
        for(int i = 0;i<productName.size();i++)
        {
            String str = "";
            str+=productName.get(i);
            str+=" "+cmimi.get(i);
            str+=" "+sasia.get(i);
            //write.get(i).setText(str);
            bigcontainer.addView(CreateblockonShporta.createblock(context,productName.get(i),Double.toString(cmimi.get(i)),Integer.toString(sasia.get(i)),Double.toString(total_.get(i))));

        }

        total_cmim.setText("$"+Double.toString(total));

    }
    public void goBack(View view){

        super.onBackPressed();
        overridePendingTransition(0,0);



    }
    public void goHome(View view) {

        startActivity(new Intent(view.getContext(), MainActivity.class));
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
