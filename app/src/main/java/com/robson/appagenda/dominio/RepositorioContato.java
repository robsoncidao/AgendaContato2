package com.robson.appagenda.dominio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

/**
 * Created by CIDAO on 04/10/2016.
 */
public class RepositorioContato {
    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn){
        this.conn = conn;
    }

    public ArrayAdapter<String> buscaContatos(Context context) {

        ArrayAdapter<String> adaptadorContatos = new ArrayAdapter<String>( context, android.R.layout.simple_list_item_1 );

        Cursor cursor = conn.query( "CONTATO", null, null, null, null, null, null );

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String telefone = cursor.getString( 1 );
                adaptadorContatos.add( telefone );
            } while (cursor.moveToNext());
        }
        return adaptadorContatos;
    }
}
