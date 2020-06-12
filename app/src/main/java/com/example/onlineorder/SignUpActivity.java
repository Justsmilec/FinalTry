package com.example.onlineorder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.onlineorder.LoginActivity.ratioY;
import static com.example.onlineorder.LoginActivity.session;

public class SignUpActivity extends AppCompatActivity {

    Button buttonSignUp;
    EditText username, userpassword;
    static String str_username = "";
    static String str_password = "";
    Connection conn1 = null;
    Statement stm1 = null;
    Statement check_ifExist = null;
    ResultSet res;
    TextView txt_span;
    boolean isKeyboardShown = false;
    ConstraintLayout contentView;
    LinearLayout container;


    public  static String OrderingUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        username = (EditText) findViewById(R.id.textInputEditText);
        userpassword = (EditText) findViewById(R.id.editText);
        contentView = (ConstraintLayout) findViewById(R.id.contentView);
        container = (LinearLayout) findViewById(R.id.container2);
        txt_span = (TextView) findViewById(R.id.textView4);
        set_spannable();
        Timer timer1 = new Timer();
        timer1.schedule(new SayHello(), 0, 10);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new Task(SignUpActivity.this).execute();
            }


        });

    }


    class Task extends AsyncTask<String, String, String> {
        public String message;
        boolean error = false;
        String str_username = username.getText().toString();
        String str_password = userpassword.getText().toString();

        ResultSet res = null;
        Context context;

        public Task(Context _context){
            this.context = _context;
        }
        protected void onPreExecute()
        {
            super.onPreExecute();
            System.out.println("dsfdsfdg");

        }


        @Override
        protected String doInBackground(String... strings) {
            SignUpActivity.OrderingUser = str_username;
            try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();

                //DriverManager.registerDriver();
                conn1 = DriverManager.getConnection("jdbc:mysql://10.0.2.2/luarasi", "root", "12345elb");
                stm1 = conn1.createStatement();

                check_ifExist = conn1.createStatement();
                res = check_ifExist.executeQuery("select * from luarasi_table where user_name like  '" + str_username + "'");


                if (res.isBeforeFirst()) {
                    message = "Actual Username exist.Sign up with another one";
                    error = true;
                }
                else {

                    if (str_username != "" && str_password != "") {

                        stm1.executeUpdate("INSERT INTO luarasi_table (user_name,user_password) VALUES ('" + str_username + "','" + str_password + "')");
                        session.createLoginSession(str_username,str_password);
                        error = false;
                        conn1.close();

                    } else {
                        message = "Fill both spaces";
                        error = true;
                    }


                }

                return message;
            }

            catch(Exception e){
                Log.d("My Error", "doInBackground: ----" + e.toString());
            }


            return null;
        }

        private void alertView( String message ) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(SignUpActivity.this);
            dialog.setTitle( "Hello" )

                    .setMessage(message)

                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
        }


        @Override
        protected void onPostExecute(String message) {
            if(error)
            {
                    System.out.println("sdfgdfgdfgdfg");
                alertView(message);
            }
            else
            {
                // user is not logged in redirect him to Login Activity
                Intent i = new Intent(context, MainActivity.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                context.startActivity(i);
                finish();
            }

        }
    }


    class SayHello extends TimerTask {
        public void run() {
            contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int heightDiff = (int)(contentView.getRootView().getHeight()/ratioY) - (int)(contentView.getHeight()/ratioY);

                    if (heightDiff > 250/ratioY) {
                        isKeyboardShown = true;

                    } else {
                        isKeyboardShown  = false;
                    }
                }
            });

        }
    }


    public void set_spannable(){
        String value = "<html>I have an account.<span style = 'color:blue; font-size:20px'> Sign In</span></html>";
        txt_span.setText(Html.fromHtml(value));
        SpannableString my_str =  new SpannableString(txt_span.getText());
        my_str.setSpan(new ClickableText(getApplicationContext(),LoginActivity.class), 18, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_span.setText(my_str);
        txt_span.setMovementMethod(LinkMovementMethod.getInstance());
    }



}
