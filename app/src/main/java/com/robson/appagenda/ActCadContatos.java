package com.robson.appagenda;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.robson.appagenda.database.DataBase;
import com.robson.appagenda.dominio.RepositorioContato;
import com.robson.appagenda.dominio.entidades.Contato;

import java.util.Date;

public class ActCadContatos extends AppCompatActivity {

    //Campos da agenda
    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtDatasEspeciais;
    private EditText edtGrupos;

    //Criando os Spinner
    private Spinner spnTipoTelefone;
    private Spinner spnTipoEmail;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDataEspeciais;

    // Armazenar as opções escolhidas em cada Spinner no ArrayAdapter
    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDatasEspeciais;

    //Variaveis de conexão com o banco
    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;
    private Contato contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contatos);

        //Iniciando os campos da agenda
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtDatasEspeciais = (EditText) findViewById(R.id.edtDatasEspeciais);
        edtGrupos = (EditText) findViewById(R.id.edtGrupos);

        //Iniciando as opções de cada campo da agenda
        spnTipoTelefone = (Spinner) findViewById(R.id.spnTipoTelefone);
        spnTipoEmail = (Spinner) findViewById(R.id.spntTipEmail);
        spnTipoEndereco = (Spinner) findViewById(R.id.spnTipoEndereco);
        spnTipoDataEspeciais = (Spinner) findViewById(R.id.spnTipoDatasEspeciais);

        //Iniciando os ArrayAdapter<>
        adpTipoEmail = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoTelefone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEndereco = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoDatasEspeciais = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoDatasEspeciais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Associando o Spnnier ao ArrayAdapter
        spnTipoEmail.setAdapter(adpTipoEmail);
        spnTipoTelefone.setAdapter(adpTipoTelefone);
        spnTipoEndereco.setAdapter(adpTipoEndereco);
        spnTipoDataEspeciais.setAdapter(adpTipoDatasEspeciais);

        //Adicionando os itens de cada Spnnier (menu de opções que oplicativo oferece na hora de cadastrar novos contatos)
        //Spnnier E-mail
        adpTipoEmail.add("Pessoal");
        adpTipoEmail.add("Trabalho");
        adpTipoEmail.add("Outros");

        //Spnnier Telefone
        adpTipoTelefone.add("Celular");
        adpTipoTelefone.add("Trabalho");
        adpTipoTelefone.add("Casa");
        adpTipoTelefone.add("Principal");

        //Spnnier Endereço
        adpTipoEndereco.add("Casa");
        adpTipoEndereco.add("Trabalho");
        adpTipoEndereco.add("Outros");

        //Spnnier Datas Especiais
        adpTipoDatasEspeciais.add("Aniversário");
        adpTipoDatasEspeciais.add("Data comemorativa");
        adpTipoDatasEspeciais.add("Outros");

        try {
            dataBase = new DataBase(this);

            //criação da leitura, consulta e escrita do banco de dados
            conn = dataBase.getWritableDatabase();
            repositorioContato = new RepositorioContato(conn);

        } catch (SQLException ex) {
            //SQLException importante usar o pacote (import android.database.SQLException;) android
            // pois o pacote java não tem suporte.
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco de dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_cad_contatos, menu);
        return true;
    }

    // Criação das ações de menu da tela de cadastro de contato responsavel por controlar qual menu foi selecionado
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Opções de ação do menu na tela de cadastro de contato
        switch (item.getItemId()) {
            case R.id.mni_acao1:
                    if (contato == null){
                        inserirContato();
                    }
                finish();
                break;
            case R.id.mni_acao2:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private  void inserirContato(){

        try {
            contato = new Contato();

            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());
            Date date = new Date();
            contato.setDatasEspeciais(date);
            contato.setGrupos(edtGrupos.getText().toString());

            contato.setTipoTelefone("");
            contato.setTipoEmail("");
            contato.setTipoEndereco("");
            contato.setTipoDatasEspeciais("");


            repositorioContato.inserir(contato);
        }catch (Exception ex){
            //SQLException importante usar o pacote (import android.database.SQLException;) android
            // pois o pacote java não tem suporte.
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
