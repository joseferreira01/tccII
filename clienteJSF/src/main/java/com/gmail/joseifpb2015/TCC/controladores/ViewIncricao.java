/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.clientrest.InscricaoClientRest;
import com.gmail.joseifpb2015.TCC.utilitario.Mensagem;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.json.JSONException;

/**
 *
 * @author jose
 */
@RequestScoped
@Named
public class ViewIncricao {

    private Inscricao inscricao = new Inscricao();
    private String idIncricao;
    @Inject
    private InscricaoClientRest rest;
    @Inject
    private Mensagem msg;

    @PostConstruct
    public void iniciar() {
        try {
            this.idIncricao = FacesContext.
                    getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap().
                    get("idevento");
            this.inscricao = rest.buscarPorId(idIncricao);
            System.err.println("ssssss "+inscricao);
        } catch (JSONException ex) {
            Logger.getLogger(ViewIncricao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }

    public String getIdIncricao() {
        return idIncricao;
    }

    public void setIdIncricao(String idIncricao) {
        this.idIncricao = idIncricao;
    }
    public String entrar(){
        return null;
    }
    public String autorizar(){
        msg.addMessage("Participante aotorizado");
        return "edit-atividada?faces-redirect=true";
    }

}
