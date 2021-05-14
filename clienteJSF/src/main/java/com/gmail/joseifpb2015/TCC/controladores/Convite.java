/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author jose
 */
@Document
public class Convite implements Serializable {

    @Id
    private String id;
    private String remetente;
    private String emailremetente;
    private String emailDoConvidado;
    private String nomeDoConvidado;
    private String idEvento;
    private String descricaoEvento;
    private String dataEvento;
    private String tituloEvento;
    private String terminoEvento;
    @Enumerated(EnumType.STRING)
    TipoConvidado tipo;
    @Enumerated(EnumType.STRING)
    StatusConvite statusConvite;

    public Convite() {
        this.statusConvite = StatusConvite.AGUARDANDO;
    }

    public Convite(String remetente, String Emailremetente, String emailDoConvidado, String idEvento, String descricaoEvento, String dataEvento, String terminoEvento, TipoConvidado tipo, StatusConvite statusConvite) {
        this();
        this.remetente = remetente;
        this.emailremetente = Emailremetente;
        this.emailDoConvidado = emailDoConvidado;
        this.idEvento = idEvento;
        this.descricaoEvento = descricaoEvento;
        this.dataEvento = dataEvento;
        this.terminoEvento = terminoEvento;
        this.tipo = tipo;
        this.statusConvite = statusConvite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getEmailremetente() {
        return emailremetente;
    }

    public void setEmailremetente(String emailremetente) {
        this.emailremetente = emailremetente;
    }

    public String getEmailDoConvidado() {
        return emailDoConvidado;
    }

    public void setEmailDoConvidado(String emailDoConvidado) {
        this.emailDoConvidado = emailDoConvidado;
    }

    public String getNomeDoConvidado() {
        return nomeDoConvidado;
    }

    public void setNomeDoConvidado(String nomeDoConvidado) {
        this.nomeDoConvidado = nomeDoConvidado;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String TituloEvento) {
        this.tituloEvento = TituloEvento;
    }

    public String getTerminoEvento() {
        return terminoEvento;
    }

    public void setTerminoEvento(String terminoEvento) {
        this.terminoEvento = terminoEvento;
    }

    public TipoConvidado getTipo() {
        return tipo;
    }

    public void setTipo(TipoConvidado tipo) {
        this.tipo = tipo;
    }

    public StatusConvite getStatusConvite() {
        return statusConvite;
    }

    public void setStatusConvite(StatusConvite statusConvite) {
        this.statusConvite = statusConvite;
    }

    @Override
    public String toString() {
        return "Convite{" + "id=" + id + ", remetente=" + remetente + ", emailremetente=" + emailremetente + ", emailDoConvidado=" + emailDoConvidado + ", nomeDoConvidado=" + nomeDoConvidado + ", idEvento=" + idEvento + ", descricaoEvento=" + descricaoEvento + ", dataEvento=" + dataEvento + ", TituloEvento=" + tituloEvento + ", terminoEvento=" + terminoEvento + ", tipo=" + tipo + ", statusConvite=" + statusConvite + '}';
    }

  
}
