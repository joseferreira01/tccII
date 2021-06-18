/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.utilitario.Mensagem;
import com.gmail.joseifpb2015.TCC.clientrest.EventoClientRest;
import com.gmail.joseifpb2015.TCC.clientrest.FormClientRest;
import com.gmail.joseifpb2015.TCC.clientrest.InscricaoClientRest;
import com.gmail.joseifpb2015.TCC.entidades.Campo;
import com.google.gson.Gson;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.primefaces.event.FlowEvent;
import org.primefaces.json.JSONException;

/**
 *
 * @author jose2
 */
@Named
@SessionScoped
public class EventoCotrolador implements Serializable {
    
    @Inject
    private EventoClientRest servico;
    @Inject
    private FormClientRest formClientRest;
    private Evento evento;
    private Convite convite;
    private byte foto[];
    private List<Convite> convites;
    @Inject
    private Mensagem msg;
    private String date;
    private String conv;
    private String date2;
    private Part part;
    private List<Evento> eventos;
    private List<Atividade> atividades;
    HashMap<String, String> estados;
    HashMap<String, String> formato;

    public HashMap<String, String> getFormato() {
        return formato;
    }

    public void setFormato(HashMap<String, String> formato) {
        this.formato = formato;
    }
    HashMap<String, String> tipoAtividade;
    HashMap<String, String> tipoConvite;
    private int next = 1;
    private int nextAt = 1;
    private boolean skip;
    private Atividade atividade;
    private Usuario usuario;
    @Inject
    private InscricaoClientRest clientRest;
    private Inscricao inscricao;
    
    @PostConstruct
    public void posCon() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.inscricao = new Inscricao();
        this.atividade = new Atividade();
        evento = new Evento();
        convite = new Convite();
        tipoAtividade = new HashMap<>();
        tipoConvite = new HashMap<>();
        estados = new HashMap<>();
        estados.put("PB", "Paraíba");
        estados.put("SP", "São Paulo");
        estados.put("RJ", "Rio de Janeiro");
        
        formato = new HashMap<>();
        
        formato.put("Presencial","Presencial");
        formato.put("online", "online");
        formato.put("hibrido", "hibrido");
       
       
       
        
        tipoAtividade.put("palestra", "palestra");
        tipoAtividade.put("palestra", "palestra");
        tipoAtividade.put("aula", "aula");
        tipoAtividade.put("mostra", "mostra");
        tipoConvite.put(TipoConvidado.PALESTRANTE.name(), TipoConvidado.PALESTRANTE.name());
        tipoConvite.put(TipoConvidado.COLABORADOR.name(), TipoConvidado.COLABORADOR.name());
        tipoConvite.put(TipoConvidado.PARTICIPANTE.name(), TipoConvidado.PARTICIPANTE.name());
        
        try {
            convites = new ArrayList<>();
            // eventosPage(next);
            this.atividades = new ArrayList<>(servico.listAtividades(evento.getId(), 0));
            eventos = servico.todos();
            
        } catch (JSONException ex) {
            Logger.getLogger(EventoCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void eventosPage(int page) throws JSONException {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        eventos = servico.eventosPorOrganizador(usuario.getId(), page - 1);
        if (eventos.size() < 1 || next < 1) {
            next = 1;
            eventos = servico.eventosPorOrganizador(usuario.getId(), 0);
        }
    }
    
    public void atividadePage(int page) throws JSONException {
        System.err.println("contro lisst atividade");
//  Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        atividades = servico.listAtividades(evento.getId(), page - 1);
        if (eventos.size() < 1 || next < 1) {
            next = 1;
            atividades = servico.listAtividades(evento.getId(), 0);
        }
    }
    
    public String editarAtividade(Atividade atividade) {
        this.atividade = atividade;
        System.err.println("editar atividade");
        return "edit-atividada?faces-redirect=true";
    }
    
    public String criarAtividade() {
        this.atividade = new Atividade();
        System.err.println("editar atividade");
        return "criar-atividada?faces-redirect=true";
    }
    
    public boolean validarData(String data) {
        //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        formatter.setLenient(false);
        try {
            formatter.parse(date);
            
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    public HashMap<String, String> getTipoAtividade() {
        return tipoAtividade;
    }
    
    public void setTipoAtividade(HashMap<String, String> tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }
    
    public List<Atividade> getAtividades() {
        
        try {
            return servico.listAtividades(evento.getId(), 0);
        } catch (JSONException ex) {
            Logger.getLogger(EventoCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.EMPTY_LIST;
    }
    
    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
    
    public Atividade getAtividade() throws JSONException {
        atividades = servico.listAtividades(evento.getId(), 0);
        return atividade;
    }
    
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    
    public String nextPage() throws JSONException {
        next++;
        eventosPage(next);
        return null;
    }
    
    public String previous() throws JSONException {
        next--;
        eventosPage(next);
        return null;
        
    }
    
    public List<Evento> getEventos() {
        return eventos;
    }
    
    public HashMap<String, String> getTipoConvite() {
        return tipoConvite;
    }
    
    public void setTipoConvite(HashMap<String, String> tipoConvite) {
        this.tipoConvite = tipoConvite;
    }
    
    public String editar(Evento evento) {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.evento = evento;
        
        if (usuario.getId() == null ? evento.getOrganizador() == null : usuario.getId().equals(evento.getOrganizador())) {
            System.err.println("pagina de edi " + evento);
            return "edit-evento?faces-redirect=true";
        }
        return "ver?faces-redirect=true";
        
    }
    public String home(Evento e){
        this.evento = e;
         return "ver?faces-redirect=true";
    }
    
    public byte[] getFoto() {
        return foto;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    public String cadastrar() {
        
        try {
            if (!validarData(date) && validarData(date2)) {
                System.err.println("data vlide");
                msg.addMessage("data invalida");
                return null;
            }
            System.out.println(part.getContentType());
            
            byte[] arquivoByte = toByteArrayUsingJava(part.getInputStream());
            evento.setCapa(base64(arquivoByte));
            Image image = new ImageIcon(arquivoByte).getImage();
            File file = new File("img");

            // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("img", file);
            //System.err.println("usuario da sesao "+usuario);
            evento.setDataInicio(date);
            evento.setDataTernino(date2);
            evento.setOrganizador(usuario.getId());
            //System.out.println("evento criado "+evento.getEndereco().getUF());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(evento));
            System.out.println("json evento -> " + json);
            servico.salvarEvento(json);
            eventos = servico.todos();
        } catch (IOException | JSONException e) {
            msg.addMessage("erro tente novamente");
            return null;
        }
        
        msg.addMessage("Evento salvo");
        try {
            if (!validarData(date) && validarData(date2)) {
                System.err.println("data vlide");
                msg.addMessage("data invalida");
                return null;
            }
            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //System.err.println("usuario da sesao "+usuario);
            evento.setDataInicio(date);
            evento.setDataTernino(date2);
            evento.setOrganizador(usuario.getId());
            //System.out.println("evento criado "+evento.getEndereco().getUF());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(evento));
            System.out.println("json evento -> " + json);
            servico.salvarEvento(json);
            eventos = servico.todos();
        } catch (IOException | JSONException e) {
            msg.addMessage("erro tente novamente");
            return null;
        }
        
        msg.addMessage("Evento salvo");
        try {
            if (!validarData(date) && validarData(date2)) {
                System.err.println("data vlide");
                msg.addMessage("data invalida");
                return null;
            }
            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //System.err.println("usuario da sesao "+usuario);
            evento.setDataInicio(date);
            evento.setDataTernino(date2);
            evento.setOrganizador(usuario.getId());
            //System.out.println("evento criado "+evento.getEndereco().getUF());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(evento));
            System.out.println("json evento -> " + json);
            servico.salvarEvento(json);
            eventos = servico.todos();
        } catch (IOException | JSONException e) {
            msg.addMessage("erro tente novamente");
            return null;
        }
        
        msg.addMessage("Evento salvo");
        try {
            if (!validarData(date) && validarData(date2)) {
                System.err.println("data vlide");
                msg.addMessage("data invalida");
                return null;
            }
            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //System.err.println("usuario da sesao "+usuario);
            evento.setDataInicio(date);
            evento.setDataTernino(date2);
            evento.setOrganizador(usuario.getId());
            //System.out.println("evento criado "+evento.getEndereco().getUF());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(evento));
            System.out.println("json evento -> " + json);
            servico.salvarEvento(json);
            eventos = servico.todos();
        } catch (IOException | JSONException e) {
            msg.addMessage("erro tente novamente");
            return null;
        }
        
        msg.addMessage("Evento salvo");
        try {
            if (!validarData(date) && validarData(date2)) {
                System.err.println("data vlide");
                msg.addMessage("data invalida");
                return null;
            }
            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //System.err.println("usuario da sesao "+usuario);
            evento.setDataInicio(date);
            evento.setDataTernino(date2);
            evento.setOrganizador(usuario.getId());
            //System.out.println("evento criado "+evento.getEndereco().getUF());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(evento));
            System.out.println("json evento -> " + json);
            servico.salvarEvento(json);
            eventos = servico.todos();
        } catch (IOException | JSONException e) {
            msg.addMessage("erro tente novamente");
            return null;
        }
        
        msg.addMessage("Evento salvo");
        try {
            if (!validarData(date) && validarData(date2)) {
                System.err.println("data vlide");
                msg.addMessage("data invalida");
                return null;
            }
            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //System.err.println("usuario da sesao "+usuario);
            evento.setDataInicio(date);
            evento.setDataTernino(date2);
            evento.setOrganizador(usuario.getId());
            //System.out.println("evento criado "+evento.getEndereco().getUF());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(evento));
            System.out.println("json evento -> " + json);
            servico.salvarEvento(json);
            eventos = servico.todos();
        } catch (IOException | JSONException e) {
            msg.addMessage("erro tente novamente");
            return null;
        }
        
        msg.addMessage("Evento salvo");
        try {
            if (!validarData(date) && validarData(date2)) {
                System.err.println("data vlide");
                msg.addMessage("data invalida");
                return null;
            }
            //Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //System.err.println("usuario da sesao "+usuario);
            evento.setDataInicio(date);
            evento.setDataTernino(date2);
            evento.setOrganizador(usuario.getId());
            //System.out.println("evento criado "+evento.getEndereco().getUF());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(evento));
            System.out.println("json evento -> " + json);
            servico.salvarEvento(json);
            eventos = servico.todos();
        } catch (IOException | JSONException e) {
            msg.addMessage("erro tente novamente");
            return null;
        }
        
        msg.addMessage("Evento salvo");
        this.evento = new Evento();
        return "home?faces-redirect=true";

        //return "home?faces-redirect=true";
    }
    
    public String novaAtividade() {
        try {
            
            atividade.setIdEvento(evento.getId());
            System.err.println("jsom atividade " + atividade.toString());
            Gson g = new Gson();
            StringBuffer json = new StringBuffer();
            json.append(g.toJson(atividade));
            servico.salvarAtividade(json);
        } catch (IOException e) {
            msg.addMessage("Erro tente novamrnte ");
        }
        atividade = new Atividade();
        return "edit-evento?faces-redirect=true";
    }
    
    public String novoconvite() {
        convite.setIdEvento(evento.getId());
        convite.setDescricaoEvento(evento.getDescricao());
        convite.setDataEvento(evento.getDataInicio());
        convite.setTerminoEvento(evento.getDataTernino());
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        convite.setEmailremetente(usuario.getEmail());
        convite.setRemetente(usuario.getNome());
        convite.setTituloEvento(evento.getTitulo());
        convite.setTipo(TipoConvidado.valueOf(conv));
        System.err.println("jsom atividade " + convite.toString());
        Gson g = new Gson();
        StringBuffer json = new StringBuffer();
        json.append(g.toJson(convite));
        try {
            servico.salvarConvite(json);
        } catch (IOException ex) {
            msg.addMessage("erro");
            return "edit-evento?faces-redirect=true";
        }
        msg.addMessage("salvo");
        System.err.println(convite);
        convite = new Convite();
        return "edit-evento?faces-redirect=true";
        
    }
    
    public String apagarAtividade() {
        try {
            servico.apagarAtividade(atividade.getId());
            msg.addMessage("atividade apagada");
            return "edit-evento?faces-redirect=true";
            
        } catch (IOException e) {
        }
        msg.addMessage(" Erro ao apagar atividade");
        return "edit-evento?faces-redirect=true";
    }
    
    public List<Convite> getConvites() {
        try {
            convites = servico.convitesOrganizado(evento.getId());
        } catch (JSONException ex) {
            Logger.getLogger(EventoCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return convites;
    }
    
    public void setConvites(List<Convite> convites) {
        this.convites = convites;
    }
    
    public int getNext() {
        return next;
    }
    
    public void setNext(int next) {
        this.next = next;
    }
    
    public Part getPart() {
        return part;
    }
    
    public void setPart(Part part) {
        this.part = part;
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
    
    public HashMap<String, String> getEstados() {
        return estados;
    }
    
    public void setEstados(HashMap<String, String> estados) {
        this.estados = estados;
    }
    
    public Evento getEvento() {
        return evento;
    }
    
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public Convite getConvite() {
        return convite;
    }
    
    public void setConvite(Convite convite) {
        this.convite = convite;
    }
    
    public String getConv() {
        return conv;
    }
    
    public void setConv(String conv) {
        this.conv = conv;
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
    
    public byte[] toByteArrayUsingJava(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();
        while (reads != -1) {
            baos.write(reads);
            reads = is.read();
        }
        return baos.toByteArray();
    }
    
    public String base64(byte[] bs) {
        return Base64.getEncoder().encodeToString(bs);
    }
    
    public byte[] conBytes(String code) {
        return Base64.getDecoder().decode(code);
        
    }
    
    public String getImages() throws IOException {
        
        List<String> images = new ArrayList<String>();
        
        String path = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath("/temp");
        
        for (Evento local : eventos) {
            FileOutputStream fos = new FileOutputStream(path + "/"
                    + local.getCapa() + ".jpg");
            fos.write(local.getCapa().getBytes());
            fos.close();
            return local.getCapa() + ".jpg";
        }
        
        return null;
    }
    
    public void list(Atividade ind) {
        atividade = ind;
        
    }
    
    public String inscricao(String idEvento) throws IOException, JSONException {
        Evento   e = servico.eventoKey(idEvento);;
     if(e.getForm()==null){
       
        inscricao.setIdEvento(e.getId());
        inscricao.setEmailUsuario(usuario.getEmail());
        inscricao.setTituloEvento(e.getTitulo());
        try {
            InscricaoClientRest.salvar(inscricao);
            msg.addMessage("Inscrição realizada");
        } catch (IOException ex) {
            Logger.getLogger(EventoCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "evento?faces-redirect=true";
     }
      FacesContext.getCurrentInstance().getExternalContext().redirect("../formIncricao?idevento=" + idEvento + "");
      return null;
        
    }

    private List<Campo> criar() {
        Campo text = new Campo();
        text.setOpcoes(Collections.EMPTY_LIST);
        text.setId("t1");
        text.setLabel("nome");
        text.setNome("nome");
        text.setTipo("text");
        text.setDica("Digite nome");
        Campo text2 = new Campo();
        text2.setOpcoes(Collections.EMPTY_LIST);
        text2.setId("t2");
        text2.setLabel("nome2");
        text2.setNome("nome2");
        text2.setTipo("text");
        text2.setDica(" campo dois");

//        Campo CPF = new Campo();
//        CPF.setOpcoes(Collections.EMPTY_LIST);
//        CPF.setId("CPF");
//        CPF.setLabel("CPF");
//        CPF.setNome("CPF");
//        CPF.setTipo("text");
//        CPF.setDica("000.000.000-00");
//        CPF.setPattern("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
//
//        Campo date = new Campo();
//        text.setOpcoes(Collections.EMPTY_LIST);
//        date.setId("date");
//        date.setLabel("date");
//        date.setNome("date");
//        date.setTipo("date");
//        date.setDica("Digite date");
//
//        Campo number = new Campo();
//        number.setOpcoes(Collections.EMPTY_LIST);
//        number.setId("number");
//        number.setLabel("number");
//        number.setNome("number");
//        number.setTipo("number");
//        number.setDica("Digite number");
//
//        Campo radio = new Campo();
//        radio.setOpcoes(Collections.EMPTY_LIST);
//        radio.setId("radio");
//        radio.setLabel("radio");
//        radio.setNome("radio");
//        radio.setTipo("radio");
//
//        Campo checkbox = new Campo();
//        checkbox.setOpcoes(Collections.EMPTY_LIST);
//        checkbox.setId("checkbox");
//        checkbox.setLabel("checkbox");
//        checkbox.setNome("checkbox");
//        checkbox.setTipo("checkbox");
//        checkbox.setDica("Digite nome");
//
//        Campo textarea = new Campo();
//        textarea.setOpcoes(Collections.EMPTY_LIST);
//        textarea.setId("t1");
//        textarea.setLabel("descrição");
//        textarea.setNome("textarea");
//        textarea.setTipo("textarea");
//        textarea.setDica("Digite a descrição");
//
//        List<String> opcoes = new ArrayList<>();
//        opcoes.add("PB");
//        opcoes.add("SP");
//        opcoes.add("RJ");
//
//        Campo endereco = new Campo();
//        endereco.setOpcoes(opcoes);
//        endereco.setId("date");
//        endereco.setLabel("endereco");
//        endereco.setNome("endereco");
//        endereco.setTipo("endereco");
//        endereco.setDica("Digite date");
//
//        //select
//        Campo select = new Campo();
//        select.setOpcoes(opcoes);
//        select.setId("t");
//        select.setLabel("idade");
//        select.setNome("idade");
//        select.setTipo("select");
        List<Campo> campos = new ArrayList<>();
        campos.add(text);
        campos.add(text2);
//        campos.add(textarea);
//        campos.add(select);
//        campos.add(checkbox);
//        campos.add(radio);
//        campos.add(date);
//        campos.add(number);
//        campos.add(endereco);
//        campos.add(CPF);
        
        return campos;
    }
    
    private String forme(String id) {
        Form form = new Form();
        form.setDescricao(id);
        form.setOrigem(id);
        form.setCampos(criar());
        try {
            return formClientRest.salvar(form);
        } catch (IOException ex) {
            Logger.getLogger(EventoCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(EventoCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    public String deleteEvento(Evento e){
          Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
   
          if(usuario.getId().equalsIgnoreCase(e.getOrganizador()))
              try {
                  servico.delete( "eventos/",e.getId());
                    msg.addMessage("Evento excluído");
                    eventos.remove(e);
                     return "home?faces-redirect=true";
          } catch (IOException ex) {
                  System.out.println("erro ao deletar "+ex.getMessage());
                    msg.addMessage("Erro na operação tente novamente");
              Logger.getLogger(EventoCotrolador.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
    }
    
}
