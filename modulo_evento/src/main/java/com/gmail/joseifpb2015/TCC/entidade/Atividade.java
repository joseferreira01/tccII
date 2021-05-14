/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.entidade;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author jose
 */
@Document
public class Atividade implements Serializable {

    @Id
    private String id;
    @Indexed(name = "evento nao pode ser vazio ")
    private String idEvento;
    @Indexed(name = "TITULO_UNIQUE", unique = true)
    private String titulo;
    private String descricao;
    private String tipo;
    private String dataInicio;
    private String dataFim;
    private List<String> participantes;
    private InfoAdicional infoAdicional;

    public Atividade() {
        this.participantes = new ArrayList<>();
    }

    public Atividade(String idEvento, String titulo, String descricao, String tipo, String dataInicio, String dataFim, List<String> participantes, InfoAdicional infoAdicional) {
        this();
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.participantes = participantes;
        this.infoAdicional = infoAdicional;
    }

    public void addParticipante(String idParticipante) {
        this.participantes.add(idParticipante);
    }

    public void removeParticipante(String idParticipante) {
        this.participantes.remove(idParticipante);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @NotEmpty(message = "Data inicio não pode ser vazio")
    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    @NotEmpty(message = "Data não pode ser vazio")
    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public List<String> getParticipantes() {
        return Collections.unmodifiableList(participantes);
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public InfoAdicional getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(InfoAdicional infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atividade other = (Atividade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atividade{" + "id=" + id + ", idEvento=" + idEvento + ", titulo=" + titulo + ", descricao=" + descricao + ", tipo=" + tipo + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", participantes=" + participantes + ", infoAdicional=" + infoAdicional + '}';
    }

}
