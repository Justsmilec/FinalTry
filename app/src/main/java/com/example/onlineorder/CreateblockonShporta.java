package com.example.onlineorder;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class CreateblockonShporta {
    public static View createblock(Context context,String name,String cmimi,String sasia,String total)
    {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                400));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setLayoutDirection(LinearLayout.VERTICAL);
        params.gravity = Gravity.CENTER;
        params.setMargins(20, 20, 0,15);
        TextView tv = new TextView(context);
        tv.setText(name);
        TextView tv1 = new TextView(context);
        tv1.setText("  $" + cmimi);

        TextView tv2 = new TextView(context);
        tv2.setText("  "+sasia);
        TextView tv3 = new TextView(context);
        tv3.setText("  $" + total);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.checkout_background));
        linearLayout.setPadding(40,20,40,20);
        //linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);


        linearLayout.addView(tv);
        linearLayout.addView(tv1);
        linearLayout.addView(tv2);
        linearLayout.addView(tv3);


            //linearLayout.addView(original.getChildAt(0));

        return linearLayout;
    }
}
