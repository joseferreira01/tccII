/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.utilitario.Mensagem;
import com.gmail.joseifpb2015.TCC.clientrest.EventoClientRest;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.json.JSONException;

/**
 *
 * @author jose2
 */
@Named
@SessionScoped
public class inscricaoCotrolador1 implements Serializable {

    @Inject
    private EventoClientRest servico;

    private Evento evento;
    private Inscricao inscricao;
   
    @Inject
    private Mensagem msg;
   
    private Usuario usuario;

    @PostConstruct
    public void posCon() {
        this.inscricao = new Inscricao();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//        evento = (Evento) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("evento");

        try {
          this.evento=  servico.buscarPorId((String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("idevento"));
        } catch (JSONException ex) {
            Logger.getLogger(inscricaoCotrolador1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    
}
