package com.robson.appagenda.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.robson.appagenda.dominio.entidades.Contato;

import java.util.Date;


/**
 * Created by CIDAO on 04/10/2016.
 */
public class RepositorioContato {
    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void inserir(Contato contato){
        ContentValues values = new ContentValues();
        //campos da tabela
        values.put("nome", contato.getNome());
        values.put("telefone", contato.getTelefone());
        values.put("tipoTelefone", contato.getTipoTelefone());
        values.put("email", contato.getEmail());
        values.put("tipoEmail", contato.getTipoEmail());
        values.put("endereco", contato.getEndereco());
        values.put("tipoEndereco",contato.getTipoEndereco());
        values.put("datasEspeciais", contato.getDatasEspeciais().getTime());
        values.put("tiposDatasEspeciais",contato.getTipoDatasEspeciais());
        values.put("grupos", contato.getGrupos());
        //inserindo os dados na tabela contato
        conn.insertOrThrow("contato", null, values);
    }

 /**
    public void testeInserirContato(){

        for (int i = 0; i < 5; i++) {
            ContentValues values = new ContentValues();
            values.put("telefone", "3311-9908");
            conn.insertOrThrow("contato", null, values);
        }

    } **/

    public ArrayAdapter<Contato> buscaContatos(Context context) {

        ArrayAdapter<Contato> adaptadorContatos = new ArrayAdapter<Contato>( context, android.R.layout.simple_list_item_1 );

        Cursor cursor = conn.query( "CONTATO", null, null, null, null, null, null );

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                //Instanciando o objeto Contato para poder buscar os dados inseridos no banco
                Contato contato = new Contato();

                //Buscando os dados inseridos no banco
                contato.setNome(cursor.getString(1));
                contato.setTelefone(cursor.getString(2));
                contato.setTipoTelefone(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setTipoEmail(cursor.getString(5));
                contato.setEndereco(cursor.getString(6));
                contato.setTipoEndereco(cursor.getString(7));
                contato.setDatasEspeciais(new Date(cursor.getLong(8)));
                contato.setTipoDatasEspeciais(cursor.getString(9));
                contato.setGrupos(cursor.getString(10));

                //adicionando o objeto contato ao adapter
                adaptadorContatos.add( contato );
            } while (cursor.moveToNext());
        }
        return adaptadorContatos;
    }
}
