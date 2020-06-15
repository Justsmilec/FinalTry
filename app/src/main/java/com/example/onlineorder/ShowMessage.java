package com.example.onlineorder;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class ShowMessage {
    public static void alertView(String message, String titulli, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle( titulli )

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

