/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import java.io.Serializable;
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
public class Inscricao implements Serializable{
    
    @Id
    private String id;
    @NotEmpty(message = "informe o evento para o qual deseja inscrever-se")
    private String idEvento;
    @NotEmpty(message = "id usuario nao existe")
    private String emailUsuario;
    @NotEmpty(message = "infome titulo do evento")
    private String tituloEvento;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable =false)
    private StatusInscricao status;
    private List<MockAtividades> atividades;
    private List<InfoAdicional> outros;

    public Inscricao() {
        this.status = StatusInscricao.INSCRITO;
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
        return atividades;
    }

    public void setAtividades(List<MockAtividades> atividades) {
        this.atividades = atividades;
    }

    @Override
    public String toString() {
        return "incricao{" + "id=" + id + ", idEvento=" + idEvento + ", idUsuario=" + emailUsuario + ", status=" + status + ", atividades=" + atividades + '}';
    }

    public List<InfoAdicional> getOutros() {
        return outros;
    }

    public void setOutros(List<InfoAdicional> outros) {
        this.outros = outros;
    }
    
            
}
