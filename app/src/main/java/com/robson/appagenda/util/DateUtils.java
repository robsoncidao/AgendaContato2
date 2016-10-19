package com.robson.appagenda.util;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CIDAO on 18/10/2016.
 */
public class DateUtils {
    //transformando a Data em una String
    public static Date getDate(int ano, int mes, int dia){
        Calendar calendar = Calendar.getInstance();

        calendar.set(ano, mes, dia);

        Date data = calendar.getTime();
        return data;
    }

    //transformando a Data em una String
    public static String dateToString(int ano, int mes, int dia){
        Calendar calendar = Calendar.getInstance();

        calendar.set(ano, mes, dia);

        Date data = calendar.getTime();

        //Formatando a data os formatos short exibe a data 06/11/1987 já o formato Medium exibe a data 06 de novembro de 1987
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dataFormatada = format.format(data);

        return dataFormatada;
    }

    //convertenda uma data específica
    public static String dateToString (Date date){
        //Formatando a data os formatos short exibe a data 06/11/1987 já o formato Medium exibe a data 06 de novembro de 1987
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dataFormatada = format.format(date);

        return dataFormatada;
    }
}
