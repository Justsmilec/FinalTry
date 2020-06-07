package com.example.onlineorder;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        relativeParams = (RelativeLayout.LayoutParams) container.getLayoutParams();
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
                            System.out.println("HAPUR");
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
