package com.robson.appagenda;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.robson.appagenda.database.DataBase;
import com.robson.appagenda.dominio.RepositorioContato;
import com.robson.appagenda.dominio.entidades.Contato;

public class actContato extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemClickListener{

    private EditText edtPesquisa;
    private ListView ListViewContatos;

    private DataBase dataBase;

    //variavél de conexão com o banco
    private SQLiteDatabase conn;

    private ArrayAdapter<Contato> adaptadorContatos;


    private RepositorioContato repositorioContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);

        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        ListViewContatos = (ListView) findViewById(R.id.lstContatos);


        try {
            dataBase = new DataBase(this);

            //criação da leitura, consulta e escrita do banco de dados
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

            /**
             * repositorioContato.testeInserirContato();
             * teste de inserção
             */


            adaptadorContatos = repositorioContato.buscaContatos(this);

            //exibindo os contatos na listView associando o arrayAdapter(adaptadorContatos) no listView (listViewContatos)
            ListViewContatos.setAdapter(adaptadorContatos);


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

        //o número 0(zero) cria uma referência à activity ActCadContatos.
        //startActivityForResult sempre que usado é necessário usar o método onActivityResult
        startActivityForResult(botaoAdicionar, 0);
    }


    //Método atualiza a listView sempre que a listView tiver que exibir um resultado é importante usar o método onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Adapter (adaptadorContatos) responsavel por receber o objeto contato cadastrado no banco.
        adaptadorContatos = repositorioContato.buscaContatos(this);

        //exibindo os contatos na listView associando o arrayAdapter(adaptadorContatos) no listView (listViewContatos)
        ListViewContatos.setAdapter(adaptadorContatos);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {

        Contato contato  = adaptadorContatos.getItem(posicao);

        Intent it = new Intent(this, ActCadContatos.class);

        it.putExtra("contato", contato);
        startActivityForResult(it, 0);


    }
}
