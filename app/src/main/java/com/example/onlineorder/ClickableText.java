package com.example.onlineorder;


import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;


public class ClickableText extends ClickableSpan {
    Context _context;
    public Class _destination;

    public ClickableText(Context context,Class destination)
    {
        this._context = context;
        _destination = destination;
    }

    @Override
    public void onClick(@NonNull View widget) {

        // user is not logged in redirect him to Login Activity
        Intent i = new Intent(_context, _destination);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
