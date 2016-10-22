package com.robson.appagenda;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.robson.appagenda.app.MessagemBox;
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

    private FiltrarDados filtrarDados;


    public static final String PARAMETRO_CONTATO = "contato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);

        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        ListViewContatos = (ListView) findViewById(R.id.lstContatos);

        //Associando o evento de clique da ListView usando o método setOnItemClickListener() configurado no public void onItemClick
        ListViewContatos.setOnItemClickListener(this);


        try {
            dataBase = new DataBase(this);

            //criação da leitura, consulta e escrita do banco de dados
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

            //Adpatador que preenche o contatos no listView
            adaptadorContatos = repositorioContato.buscaContatos(this);

            //exibindo os contatos na listView associando o arrayAdapter(adaptadorContatos) no listView (listViewContatos)
            ListViewContatos.setAdapter(adaptadorContatos);

            //Filtando os dados listados no listView
            filtrarDados = new FiltrarDados(adaptadorContatos);
            edtPesquisa.addTextChangedListener(filtrarDados);


        } catch (SQLException ex) {
            //SQLException importante usar o pacote (import android.database.SQLException;) android
            // pois o pacote java não tem suporte.
            MessagemBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //fechando a conexão com o banco de dados
        if (conn != null){
            conn.close();
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

        //passando o método de filtrar os dados para o listView desta forma toda vez que
        //onActivityResult for chamado será atualizado o filtra dados
        filtrarDados.setArrayAdapter(adaptadorContatos);

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

        it.putExtra(PARAMETRO_CONTATO, contato);
        startActivityForResult(it, 0);


    }
    // Método de filtro dos dados no listView
    private class FiltrarDados implements TextWatcher{

        private ArrayAdapter<Contato> arrayAdapter;

        private FiltrarDados(ArrayAdapter<Contato> arrayAdapter){
            this.arrayAdapter = arrayAdapter;
        }

        public void setArrayAdapter (ArrayAdapter<Contato> arrayAdapter){

            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //função de exibir o filtro da listView qndo é digitado alguma texto no EditText nome
            arrayAdapter.getFilter().filter(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }//fecha o método de filtro
}
