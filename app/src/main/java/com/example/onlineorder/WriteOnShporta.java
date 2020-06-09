package com.example.onlineorder;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.onlineorder.SessionManager.KEY_NAME;

public class WriteOnShporta extends AsyncTask<String,String,String> {
    Connection conn1;
    Statement stm1 = null;
    ResultSet res = null;
    ResultSet resfromProduct = null;

    Statement stmFinduser = null;

    public String ClientName = "";
    public ArrayList<String> productName = new ArrayList<>();
    public ArrayList<Integer> sasia = new ArrayList<>();
    public ArrayList<Double> cmimi = new ArrayList<>();

    public double total;



    public WriteOnShporta(String user)
    {
        ClientName = user;

    }
    protected void onPreExecute()
    {
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(String... strings) {

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //DriverManager.registerDriver();
            conn1 = DriverManager.getConnection("jdbc:mysql://10.0.2.2/luarasi", "root", "12345elb");
            stm1 = conn1.createStatement();

            //check_ifExist = conn1.createStatement();
            //res = check_ifExist.executeQuery("select * from luarasi_table where user_name like  '" + str_username + "'");

/*
res = check_ifExist.executeQuery("select * from luarasi_table where user_name like  '" + str_username + "'");
            if (res.isBeforeFirst()) {
                message = "Actual Username exist.Sign up with another one";
                error = true;
            }
 */
            stmFinduser = conn1.createStatement();
            String actualUser = LoginActivity.session.getUserDetails().get(KEY_NAME).toString();
            res = stmFinduser.executeQuery("select iduserr from luarasi_table where user_name like  '" + actualUser + "'");
            if(res.next())
            {
                int myres = res.getInt("iduserr");
                System.out.println(myres);
                //double total = price*amount;
                //stm1.executeUpdate("INSERT INTO product (iduser,product_name,product_price,product_amount,product_total) VALUES ("+myres+",'" + productName + "','" + price + "','" + amount + "','" + total + "')");
                resfromProduct = stm1.executeQuery("select * from product where iduser like  '" + myres + "'");

                while(resfromProduct.next())
                {
                    productName.add(resfromProduct.getString("product_name"));
                    sasia.add(resfromProduct.getInt("product_amount"));
                    cmimi.add(resfromProduct.getDouble("product_price"));

                }
                System.out.println(resfromProduct.getRow());
                System.out.println(productName);

                //total = cmimi*sasia;


            }

            conn1.close();





            return null;
        }

        catch(Exception e){
            Log.d("My Error", "doInBackground: ----" + e.toString());
        }


        return null;
    }



    @Override
    protected void onPostExecute(String message) {
        String str = "";
        //str+=productName.get(1);
        //str+=" "+cmimi.get(1);
        //str+=" "+sasia.get(1);
        ShportaActivity.write(productName,sasia,cmimi);
    }
}
