/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.utilitario;

import com.gmail.joseifpb2015.TCC.controladores.Endereco;
import java.io.Serializable;

/**
 *
 * @author jose
 */
public class DadosPessoais implements Serializable{
    private String CPF;
    private String telFixo;
    private String celular;
    private  String nascionalidade;
    private String sexo;
    private String nascimento;
    private Endereco endereco;
    private Formacao formacao;

    public DadosPessoais() {
    }

    public DadosPessoais(String CPF, String telFixo, String nascionalidade, Endereco endereco, Formacao formacao) {
        this.CPF = CPF;
        this.telFixo = telFixo;
        this.nascionalidade = nascionalidade;
       
        this.endereco = endereco;
        this.formacao = formacao;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelFixo() {
        return telFixo;
    }

    public void setTelFixo(String telFixo) {
        this.telFixo = telFixo;
    }

    public String getNascionalidade() {
        return nascionalidade;
    }

    public void setNascionalidade(String nascionalidade) {
        this.nascionalidade = nascionalidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

   
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    

    @Override
    public String toString() {
        return "DadosPessoais{" + "CPF=" + CPF + ", telFixo=" + telFixo + ", nascionalidade=" + nascionalidade + ", sexo=" + sexo + ", endereco=" + endereco + ", formacao=" + formacao + '}';
    }
    
    
}
