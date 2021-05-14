/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.entidades.Campo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author jose
 */
@Document
public class Form implements Serializable {

    @Id
    private String id;
    private String origem;
    private String descricao;
    private List<Campo> campos;

    public Form() {
        this.campos = new ArrayList<>();
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public void addCampo(Campo campo) {
        this.campos.add(campo);
    }

    public void removeCampo(Campo campo) {
        this.campos.remove(campo);
    }

    public List<Campo> getCampos() {
        return Collections.unmodifiableList(campos);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
