package com.robson.appagenda.app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by CIDAO on 18/10/2016.
 */
public class ViewHelper {

    public static ArrayAdapter<String> criadorArrayAdapter(Context context, Spinner spinner){
        //Iniciando os ArrayAdapter<>
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Associando o ArrayAdapter ao Spinner
        spinner.setAdapter(arrayAdapter);
        return arrayAdapter;
    }
}
