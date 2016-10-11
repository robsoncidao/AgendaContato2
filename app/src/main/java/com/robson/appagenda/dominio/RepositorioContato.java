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

    public RepositorioContato(SQLiteDatabase conn) {
        this.conn = conn;
    }

    private ContentValues preencheContentValues(Contato contato) {
        ContentValues values = new ContentValues();
        //campos da tabela
        values.put("nome", contato.getNome());
        values.put("telefone", contato.getTelefone());
        values.put("tipoTelefone", contato.getTipoTelefone());
        values.put("email", contato.getEmail());
        values.put("tipoEmail", contato.getTipoEmail());
        values.put("endereco", contato.getEndereco());
        values.put("tipoEndereco", contato.getTipoEndereco());
        values.put("datasEspeciais", contato.getDatasEspeciais().getTime());
        values.put("tiposDatasEspeciais", contato.getTipoDatasEspeciais());
        values.put("grupos", contato.getGrupos());
        return values;
    }

    public void inserirContato(Contato contato) {
        ContentValues values = preencheContentValues(contato);
        //inserindo os dados na tabela contato
        conn.insertOrThrow("contato", null, values);
    }

    public void alterarContato(Contato contato) {

        ContentValues values = preencheContentValues(contato);
        //alterando os dados na tabela contato
        // caso tenha mais algum parâmetro é usar exempo "nome = ? AND telefone = ?" nome e telefone são campos da tabela.
        // o último parâmetro do update só aceita String.
        conn.update("contato", values, "_id = ?", new String[]{String.valueOf(contato.getId())});
    }

    public void excluir (long id){
        conn.delete("contato", "_id = ?", new String[]{String.valueOf(id)});
    }

    public ArrayAdapter<Contato> buscaContatos(Context context) {

        ArrayAdapter<Contato> adaptadorContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("contato", null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                //Instanciando o objeto Contato para poder buscar os dados inseridos no banco
                Contato contato = new Contato();

                //Buscando os dados inseridos no banco
                //cursor.getColumnIndex(contato.NOME) ColumnIndex é usado para buscar o indice da coluna
                // e contato.NOME é uma constante criada na classe contato
                contato.setId(cursor.getLong(cursor.getColumnIndex(contato.ID)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(contato.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(contato.TELEFONE)));
                contato.setTipoTelefone(cursor.getString(cursor.getColumnIndex(contato.TIPOTELEFONE)));
                contato.setEmail(cursor.getString(cursor.getColumnIndex(contato.EMAIL)));
                contato.setTipoEmail(cursor.getString(cursor.getColumnIndex(contato.TIPOEMAIL)));
                contato.setEndereco(cursor.getString(cursor.getColumnIndex(contato.ENDERECO)));
                contato.setTipoEndereco(cursor.getString(cursor.getColumnIndex(contato.TIPOENDERECO)));
                contato.setDatasEspeciais(new Date(cursor.getLong(cursor.getColumnIndex(contato.DATASESPECIAIS))));
                contato.setTipoDatasEspeciais(cursor.getString(cursor.getColumnIndex(contato.TIPODATASESPECIAIS)));
                contato.setGrupos(cursor.getString(cursor.getColumnIndex(contato.GRUPOS)));

                //adicionando o objeto contato ao adapter
                adaptadorContatos.add(contato);
            } while (cursor.moveToNext());
        }
        return adaptadorContatos;
    }


}
