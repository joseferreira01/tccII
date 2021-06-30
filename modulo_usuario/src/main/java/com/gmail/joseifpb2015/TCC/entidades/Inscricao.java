/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.entidades;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author jose
 */
@Document
public class Inscricao implements Serializable {

    @Id
    private String id;
    @NotEmpty(message = "informe o evento para o qual deseja inscrever-se")
    private String idEvento;
    @NotEmpty(message = "id usuario nao existe")
    private String emailUsuario;
    @NotEmpty(message = "infome titulo do evento")
    private String tituloEvento;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StatusInscricao status;
    private List<MockAtividades> atividades;
    private String foto;
    private String nome;
    private String QRcode;
   
    private List<InfoAdicional> outros;

    public Inscricao() {
        this.outros = new ArrayList<>();
        this.atividades = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String EmailUsuario) {
        this.emailUsuario = EmailUsuario;
    }

    public StatusInscricao getStatus() {
        return status;
    }

    public void setStatus(StatusInscricao status) {
        this.status = status;
    }

    public List<MockAtividades> getAtividades() {
        return Collections.unmodifiableList(atividades);
    }

    public void setAtividades(List<MockAtividades> atividades) {
        this.atividades = atividades;
    }

    @Override
    public String toString() {
        return "incricao{" + "id=" + id + ", idEvento=" + idEvento + ", idUsuario=" + emailUsuario + ", status=" + status + ", atividades=" + atividades + '}';
    }

    public List<InfoAdicional> getOutros() {
        return Collections.unmodifiableList(outros);
    }


   
    public void setOutros(List<InfoAdicional> outros) {
        this.outros = outros;
    }

  

    public void addAtividade(MockAtividades atividade) {
        this.atividades.add(atividade);
    }

    public void removeAtividade(MockAtividades atividade) {
        this.atividades.remove(atividade);
    }

    public void addInfo(InfoAdicional ia) {
        this.outros.add(ia);
    }
    public void removeInfo(InfoAdicional ia) {
        this.outros.remove(ia);
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }
    
}
