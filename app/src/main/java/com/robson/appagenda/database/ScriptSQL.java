package com.robson.appagenda.database;

import java.security.Key;

/**
 * Created by CIDAO on 04/10/2016.
 *
 * ****** CLASSE QUE CONTÉM OS SCRIPTS SQL ******
 */
public class ScriptSQL {

    public static String getCreateContato(){



        //Concatenar toda a String de criação tabela.
        //Utilizado para unir uma String grande.
        StringBuilder sqlBuilder  = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS contato( ");
        sqlBuilder.append("_id                 INTEGER NOT NULL ");
        sqlBuilder.append("PRIMARY Key AUTOINCREMENT, ");
        sqlBuilder.append("nome                VARCHAR(50),");
        sqlBuilder.append("telefone            VARCHAR(1), ");
        sqlBuilder.append("tipoTelefone        VARCHAR(1), ");
        sqlBuilder.append("email               VARCHAR(30), ");
        sqlBuilder.append("tipoEmail           VARCHAR (1), ");
        sqlBuilder.append("endereco            VARCHAR(30), ");
        sqlBuilder.append("tipoEndereco        VARCHAR(1), ");
        sqlBuilder.append("datasEspeciais      DATE, ");
        sqlBuilder.append("tiposDatasEspeciais VARCHAR(1), ");
        sqlBuilder.append("grupos              VARCHAR(30) ");
        sqlBuilder.append(");");

        return  sqlBuilder.toString();
    }

}
