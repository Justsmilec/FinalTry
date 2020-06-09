package com.example.onlineorder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {


    public static float ratioX;
    public static float ratioY;
    TextView txt_span, title,te;
    Button login_button;
    static EditText username, password;
    RelativeLayout container;
    RelativeLayout contentView;
    RelativeLayout.LayoutParams relativeParams;
    boolean isKeyboardShown = false;

    public static SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        ratioX = 1080 / width;
        ratioY = 2220 / height;

        password = (EditText) findViewById(R.id.editText);
        username = (EditText) findViewById(R.id.textInputEditText);
        login_button = (Button) findViewById(R.id.button2);
        txt_span = (TextView) findViewById(R.id.textView4);
        container = (RelativeLayout) findViewById(R.id.container);
        contentView = (RelativeLayout) findViewById(R.id.contentView);
        title = (TextView) findViewById(R.id.textView3);
        te = (TextView) findViewById(R.id.textView22);
        set_spannable();
        myChange(contentView);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task(username,password,LoginActivity.this).execute();
            }
        });

        relativeParams = (RelativeLayout.LayoutParams) container.getLayoutParams();
    }


    static public class Task extends AsyncTask<String,String,String> {
        boolean isLoggedin = true;
        Connection conn = null;
        Statement stm = null;
        String username_string;
        String password_string;
        EditText username, password;
        ResultSet res = null;
        Context context;
        Activity activity = (Activity) context;
        public Task(EditText username_edt,EditText password_edt,Context _context){
            this.username = username_edt;
            this.password = password_edt;
            this.context = _context;
        }

        @Override
        protected  void onPreExecute()
        {
            super.onPreExecute();
            username_string = username.getText().toString();
            password_string = password.getText().toString();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://10.0.2.2/user_data","root","12345elb");
                stm = conn.createStatement();

                if(username_string != "" && password_string != "")
                {
                    res = stm.executeQuery("select * from new_user where user_name like  '" + username_string + "' AND user_password like '"+ password_string +"'");
                    if(!res.isBeforeFirst())
                    {
                        isLoggedin = false;
                    }
                    else
                    {
                        isLoggedin = true;
                        session.createLoginSession(username_string,password_string);
                    }
                }
                else
                {
                    isLoggedin = false;
                }

                conn.close();
            }

            catch (Exception e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String message) {
            //change_margin();

            if(isLoggedin)

            {
                alertView("Succesfully loggedin",context);
                Intent i = new Intent(context, MainActivity.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                context.startActivity(i);

            }

            else
            {
                if(username_string == "" || password_string == "")
                    alertView("Ploteso",context);
                else
                    alertView("Not logged in",context);
            }
        }


        private void alertView(String message,Context context) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle( "Hello" )

                    .setMessage(message)
//     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//      public void onClick(DialogInterface dialoginterface, int i) {
//          dialoginterface.cancel();
//          }})
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    })
                    .create()
                    .show();
        }
    }


    public void set_spannable(){
        String value = "<html>Don't have an account?<span style = 'color:blue; font-size:15px'>Sign Up</span></html>";
        txt_span.setText(Html.fromHtml(value));
        SpannableString my_str =  new SpannableString(txt_span.getText());
        my_str.setSpan(new ClickableText(getApplicationContext(),SignUpActivity.class), 22, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_span.setText(my_str);
        txt_span.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Kur hapet tastiera ndryshon margin - at
     * @param contentView1
     */
    public void myChange(final RelativeLayout contentView1)
    {

        class SayHello extends TimerTask {
            public void run() {
                contentView1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int heightDiff = (int)(contentView1.getRootView().getHeight()/ratioY) - (int)(contentView1.getHeight()/ratioY);

                        if (heightDiff > 250/ratioY) {

                            isKeyboardShown = true;

                        } else {
                            isKeyboardShown  = false;
                        }

                    }
                });
                change_margin();
            }
        }
        Timer timer = new Timer();
        timer.schedule(new SayHello(), 0, 10);
    }


    public void change_margin()
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if(isKeyboardShown)
                {
                    relativeParams.setMargins(0, 0, 0, (int)(-50/ratioY));  // left, top, right, bottom
                    container.setLayoutParams(relativeParams);
                }
                else {
                    relativeParams.setMargins(0, 0, 0, (int)(400/ratioY));  // left, top, right, bottom
                    container.setLayoutParams(relativeParams);
                }
            }
        });
    }
}
