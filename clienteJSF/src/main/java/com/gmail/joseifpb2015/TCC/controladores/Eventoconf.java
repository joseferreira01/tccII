/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;



import com.gmail.joseifpb2015.TCC.clientrest.EventoClientRest;
import com.gmail.joseifpb2015.TCC.utilitario.Mensagem;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FlowEvent;
import org.primefaces.json.JSONException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author jose2
 */
@SessionScoped
@Named
public class Eventoconf implements Serializable {

    @Inject
    private EventoClientRest servico;

    private Evento evento;
    @Inject
    private Mensagem msg;
    private String date;
    private String date2;
    private UploadedFile file;
    private List<Evento> eventos;
    private boolean skip;
    private int next = 1;
     HashMap<String, String> cities;

    @PostConstruct
    public void posCon() {
        evento = new Evento();
         cities = new HashMap<>();
        cities.put("PB", "Paraíba");
        cities.put("SP", "São Paulo");
        cities.put("RJ", "Rio de Janeiro");

//        try {
//            // eventosPage(next);
//           // eventos = servico.todos();
//        } catch (JSONException ex) {
//            Logger.getLogger(Eventoconf.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//   
    public Eventoconf() {

    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public String cadastrar() throws IOException, JSONException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        evento.setDataInicio(date);
        evento.setDataTernino(date2);
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        //System.err.println("usuario da sesao "+usuario);
        evento.setOrganizador(usuario.getId());
        //System.out.println("evento criado "+evento.getEndereco().getUF());
        Gson g = new Gson();
        StringBuffer json = new StringBuffer();
        json.append(g.toJson(evento));
//        
           try {
            servico.salvarEvento(json);
            msg.addMessage("evento criado com sucesso! ");
        } catch (Exception e) {
             msg.addMessage("Erro verifique os dados e tente novamente! ");
        }
//        

        //return null;
         return "home?faces-redirect=true";

    }

    public HashMap<String, String> getCities() {
        return cities;
    }

    public void setCities(HashMap<String, String> cities) {
        this.cities = cities;
    }
    

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

}
