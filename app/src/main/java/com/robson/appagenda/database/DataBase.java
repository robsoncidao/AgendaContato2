package com.robson.appagenda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CIDAO on 04/10/2016.
 *
 * **************** CLASSE RESPONSÁVEL PELA CRIAÇÃO DA BASE DE DADOS ******
 */
//Essa classe necessita da extensão extends SQLiteOpenHelper
public class DataBase extends SQLiteOpenHelper {

    //Criando o construtor da classe
    public DataBase (Context context){
        //o número 1 indica a versão do banco.
        // Toda vez que fizer alguma alteração no banco esse número devera ser alterado.
        super(context, "AGENDA", null, 1);

        //Criando a tabela

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateContato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
