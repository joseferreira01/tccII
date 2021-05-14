/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.utilitario;

import java.io.Serializable;

/**
 *
 * @author jose
 */
public class Formacao implements Serializable {

    private String formacao;
    private String instituição;
    private String aria;

    public Formacao() {
    }

    public Formacao(String formacao, String instituição, String aria) {
        this.formacao = formacao;
        this.instituição = instituição;
        this.aria = aria;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getInstituição() {
        return instituição;
    }

    public void setInstituição(String instituição) {
        this.instituição = instituição;
    }

    public String getAria() {
        return aria;
    }

    public void setAria(String aria) {
        this.aria = aria;
    }

    @Override
    public String toString() {
        return "Formacao{" + "formacao=" + formacao + ", institui\u00e7\u00e3o=" + instituição + ", aria=" + aria + '}';
    }
    

}
