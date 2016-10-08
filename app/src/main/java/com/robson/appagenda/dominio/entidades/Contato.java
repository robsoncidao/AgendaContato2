package com.robson.appagenda.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CIDAO on 05/10/2016.
 */



public class Contato implements Serializable{

    //constantes com o nome das colunas da tabela contato para facilitar na busca dos dados la no método buscarDados do arquivo RespositorioContato
    // os nomes dentro da " "(aspas) tem que ser igual que está na criação da tabela.
    public static String ID = "_id";
    public static String NOME = "nome";
    public static String TELEFONE = "telefone";
    public static String TIPOTELEFONE = "tipoTelefone";
    public static String EMAIL = "email";
    public static String TIPOEMAIL = "tipoEmail";
    public static String ENDERECO = "endereco";
    public static String TIPOENDERECO = "tipoEndereco";
    public static String DATASESPECIAIS = "datasEspeciais";
    public static String TIPODATASESPECIAIS = "tiposDatasEspeciais";
    public static String GRUPOS = "grupos";




    private long id;
    private String nome;
    private String telefone;
    private String tipoTelefone;
    private String email;
    private String tipoEmail;
    private String endereco;
    private String tipoEndereco;
    private Date   datasEspeciais;
    private String tipoDatasEspeciais;
    private String grupos;


    public Contato (){
        id = 0;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Date getDatasEspeciais() {
        return datasEspeciais;
    }

    public void setDatasEspeciais(Date datasEspeciais) {
        this.datasEspeciais = datasEspeciais;
    }

    public String getTipoDatasEspeciais() {
        return tipoDatasEspeciais;
    }

    public void setTipoDatasEspeciais(String tipoDatasEspeciais) {
        this.tipoDatasEspeciais = tipoDatasEspeciais;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    //Método responsavel por exibir o contato na listView sem esse método é exibido o endereço em memória do objeto
    @Override
    public String toString(){

        return nome + " "+ telefone;
    }
}
