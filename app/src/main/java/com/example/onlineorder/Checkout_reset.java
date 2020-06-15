package com.example.onlineorder;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.onlineorder.SessionManager.KEY_NAME;

public class Checkout_reset extends AsyncTask<String,String,String> {

        boolean isLoggedin = true;
        Connection conn = null;
        Statement stm = null;
    Statement stm1 = null;

    String username_string;
        String password_string;
        EditText username, password;
        ResultSet res = null;
        Context context;
        Activity activity = (Activity) context;

        public Checkout_reset (String actual_user){
            username_string = actual_user;
        }


    @Override
    protected  void onPreExecute()
    {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://10.0.2.2/luarasi","root","12345elb");
            stm = conn.createStatement();
            stm1 = conn.createStatement();
            String actualUser = LoginActivity.session.getUserDetails().get(KEY_NAME).toString();

            res = stm.executeQuery("select iduserr from luarasi_table where user_name like  '" + actualUser + "'");

            if (res.next()){

                int id=res.getInt("iduserr");
System.out.println(id + "dsfsfdgmeeeeeeeeeeeeeeeeeeee");

                stm1.executeUpdate("DELETE  FROM product where iduser like "+id+"");
            }

            conn.close();
        }

        catch (Exception e)
        {

        }
        return null;
    }



}
