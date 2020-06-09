package com.example.onlineorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.onlineorder.SessionManager.KEY_NAME;

public class ShportaActivity extends AppCompatActivity {

    public static String prd;
    public static ArrayList<TextView> write;
    private TextView write1,write2,write3;//Ne labelat qe do shkruhet
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shporta);
        write1 = (TextView)findViewById(R.id.writeFromDB1);
        write2 = (TextView)findViewById(R.id.writeFromDB2);
        write = new ArrayList<>();
        write.add(write1);
        write.add(write2);




        String fromDbText = "dfgdfgfg";
        String actualUser = LoginActivity.session.getUserDetails().get(KEY_NAME).toString();
        System.out.println(actualUser);
        WriteOnShporta writeOnShporta = new WriteOnShporta(actualUser);
        writeOnShporta.execute();
        fromDbText+=writeOnShporta.productName;
        System.out.println("88sdfsfsdf " + prd);
        //write.setText(prd);

    }

    public static void write(ArrayList<String> productName,ArrayList<Integer> sasia,ArrayList<Double> cmimi){

        for(int i = 0;i<productName.size();i++)
        {
            String str = "";
            str+=productName.get(i);
            str+=" "+cmimi.get(i);
            str+=" "+sasia.get(i);
            write.get(i).setText(str);
        }

    }
}
