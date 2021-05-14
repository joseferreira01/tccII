/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.mongodb.client.model.geojson.Geometry;
import java.io.Serializable;

/**
 *
 * @author jose
 */


public class Endereco implements Serializable{

    private  String rua;
    private String cidade;
    private  String uf;
    private String cep;
    private String bairro;
    private int numero;
    private  Geometry localizacao;

    public Endereco() {
    }
    

    public Endereco(String rua, String cidade, String UF, String CEP, String bairro, int numero, Geometry localizacao) {
        this.rua = rua;
        this.cidade = cidade;
        this.uf = UF;
        this.cep = CEP;
        this.bairro = bairro;
        this.numero = numero;
        this.localizacao = localizacao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Geometry getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Geometry localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", cidade=" + cidade + ", UF=" + uf + ", CEP=" + cep + ", bairro=" + bairro + ", numero=" + numero + ", localizacao=" + localizacao + '}';
    }

   
}

