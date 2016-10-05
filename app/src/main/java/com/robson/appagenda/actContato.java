package com.robson.appagenda;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.robson.appagenda.database.DataBase;
import com.robson.appagenda.dominio.RepositorioContato;

public class actContato extends AppCompatActivity {

    private EditText edtPesquisa;
    private ListView lstContatos;

    private DataBase dataBase;

    //variavél de conexão com o banco
    private SQLiteDatabase conn;

    private ArrayAdapter<String> adaptadorContatos;


    private RepositorioContato repositorioContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);

        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);


        try {
            dataBase = new DataBase(this);

            //criação da leitura, consulta e escrita do banco de dados
            conn = dataBase.getReadableDatabase();

            repositorioContato = new RepositorioContato(conn);

            adaptadorContatos = repositorioContato.buscaContatos(this);

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexão criada com sucesso");
            dlg.setNeutralButton("OK", null);
            dlg.show();


        } catch (SQLException ex) {
            //SQLException importante usar o pacote (import android.database.SQLException;) android
            // pois o pacote java não tem suporte.
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco de dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

    }

    public void botaoAdicionar(View view) {

        Intent botaoAdicionar = new Intent(this, ActCadContatos.class);
        startActivity(botaoAdicionar);
    }

}
