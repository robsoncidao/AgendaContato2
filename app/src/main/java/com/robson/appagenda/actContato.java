package com.robson.appagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class actContato extends AppCompatActivity {

    private EditText edtPesquisa;
    private ListView lstContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);

        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);
    }

    public void botaoAdicionar (View view){

        Intent botaoAdicionar = new Intent(this, ActCadContatos.class);
        startActivity(botaoAdicionar);
    }

}
