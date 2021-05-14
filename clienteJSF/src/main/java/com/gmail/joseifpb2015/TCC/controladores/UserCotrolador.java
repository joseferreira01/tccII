/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.clientrest.UserClientRest;
import com.gmail.joseifpb2015.TCC.utilitario.Mensagem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.json.JSONException;

/**
 *
 * @author jose2
 */
@Named
@RequestScoped
public class UserCotrolador {

    private File file;
    @Inject
    private UserClientRest servico;

    private Usuario usuario;
    @Inject
    private Mensagem msg;

    @PostConstruct
    public void posCon() {
        usuario = new Usuario();
        file = new File("/.");
    }

    public UserCotrolador() {

    }

    public String login() throws JSONException, IOException {
        System.err.println("receb " + usuario.getEmail());
        //System.err.println("controle " + servico.todos());
        Usuario result = servico.logar(usuario, "login");
        System.err.println("logando " + result);
        if (result != null && result.getId() != null) {

            if (result.getStatus().equals(Status.DESABILITADO)) {
                msg.addMessage("Usuário inativo");
                return "habiltar?faces-redirect=true";
            } else {
                usuario = result;
                msg.addMessage("Bem vindo " + usuario.getNome() + "!");
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                return "home?faces-redirect=true";
            }
        }
        msg.addMessage("email ou senha inválido ");
        return null;

    }

    public String habilitar() {
        try {
            servico.habilitar(usuario);
            msg.addMessage("Bem vindo, tudo certo, agora faça login e aproveite " + "!");
            return "index?faces-redirect=true";
        } catch (IOException ex) {
            Logger.getLogger(UserCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(UserCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        msg.addMessage("Ops ocorreu com erro: verifique o codigo ou click em reenviar código");
        return null;
    }

    public String alterarSenha() {
        try {
            Usuario resul = servico.buscarPorId(usuario.getId());
            resul.setSenha(usuario.getSenha());
            servico.aualizarUsuario(resul);

            msg.addMessage("Bem vindo, tudo certo, agora faça login e aproveite " + "!");
            return "index?faces-redirect=true";
        } catch (JSONException | IOException ex) {
            Logger.getLogger(UserCotrolador.class.getName()).log(Level.SEVERE, null, ex);

            msg.addMessage("Ops ocorreu com erro: verifique o codigo ou click em reenviar código");
            return null;
        }
    }

    public String logout() {
        System.err.println("logout -----------------------------------");
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        return "index?faces-redirect=true";
    }

    public String cadastrar() throws IOException {
        System.out.println("usuario " + usuario);
        servico.salvar(usuario);
        msg.addMessage("Cadastro realizado com sucesso!");
        return "/habiltar?faces-redirect=true";

    }

    public String env() {
        try {
            servico.reeviarEvail(usuario, "reenviaremail");
            msg.addMessage(" email enviado com sucesso");
            return "habiltar?faces-redirect=true";
        } catch (IOException ex) {
            msg.addMessage(" erro tente novamente email ");
            return null;
        } catch (JSONException ex) {
            Logger.getLogger(UserCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public String criarConta() {

        return "registro?faces-redirect=true";

    }

    public String reeviarEmail() {

        try {
            servico.reeviarEvail(usuario, "reenviaremail");
            msg.addMessage(" email enviado com sucesso");

            return "al-senha?faces-redirect=true";
        } catch (IOException | JSONException ex) {
            Logger.getLogger(UserCotrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        msg.addMessage("Erro tente novamente");
        return null;

    }

    public String novaSenha() {

        try {
            servico.recuperarSenha(usuario);
            msg.addMessage("Senha alterada com sucesso!");
            return "index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
        //msg.addMessage("Erro tente novamente");

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    
}
