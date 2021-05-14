/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.utilitario;

import java.util.List;

/**
 *
 * @author jose
 */
public class forn {
    private String titulo;
    private String name;
    private String tipo;
    private List<String> opcao;

    public forn() {
    }

    public forn(String titulo, String name, String tipo, List<String> opcao) {
        this.titulo = titulo;
        this.name = name;
        this.tipo = tipo;
        this.opcao = opcao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getOpcao() {
        return opcao;
    }

    public void setOpcao(List<String> opcao) {
        this.opcao = opcao;
    }
    
    
}
