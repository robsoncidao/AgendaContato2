package com.robson.appagenda.app;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by CIDAO on 17/10/2016.
 */
public class MessagemBox {

    public static void showAlert (Context context, String title, String mensagem){
        show(context, title, mensagem, android.R.drawable.ic_dialog_alert);
    }

    public static void show (Context context, String title, String mensagem){
        show(context, title, mensagem, 0);
    }

    public static void show (Context context, String title, String mensagem, int iconId){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(context);
        alertaDialog.setIcon(iconId);
        alertaDialog.setTitle(title);
        alertaDialog.setMessage(mensagem);
        alertaDialog.setNeutralButton("OK", null);
        alertaDialog.show();
    }
}
