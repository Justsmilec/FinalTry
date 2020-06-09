package com.example.onlineorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.onlineorder.SessionManager.KEY_NAME;

public class WriteOrder extends AsyncTask<String,String,String> {
    Connection conn1;
    Statement stm1 = null;
    ResultSet res = null;
    Statement stmFinduser = null;
    String productName;
    double price;
    int amount;
    String user;
    public WriteOrder(String productName,double price,int amount,String user)
    {
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.user = user;
    }
    protected void onPreExecute()
    {
        super.onPreExecute();
        System.out.println("dsfdsfdg");

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
                        double total = price*amount;
                        stm1.executeUpdate("INSERT INTO product (iduser,product_name,product_price,product_amount,product_total) VALUES ("+myres+",'" + productName + "','" + price + "','" + amount + "','" + total + "')");
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


    }
}
