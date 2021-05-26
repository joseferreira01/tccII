/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidades.Inscricao;
import com.gmail.joseifpb2015.TCC.entidades.StatusInscricao;
import com.gmail.joseifpb2015.TCC.repositorio.InscricaoRepositorio;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Configuration
@PropertySource("classpath:env/serve.properties")
@Service
public class InscricaoServicoImpl extends ServicoImpl<Inscricao, String> implements InscricaoServico {

    @Autowired
    private InscricaoRepositorio repositorio;
    @Autowired
    private Environment env;
    @Autowired
    private ClientRestFull<Object> restFull;

    @Override
    public List<Inscricao> buscarPorEmail(String id, Pageable pageable) {
        return repositorio.findByEmailUsuario(id, pageable);
    }

    @Override
    public List<Inscricao> buscarPorIDEvento(String id, Pageable pageable) {
        return repositorio.findByidEvento(id, pageable);
    }

    @Override
    public List<Inscricao> buscarPorIDEventoEStatus(String id, StatusInscricao status, Pageable pageable) {
        return repositorio.findByidEventoAndStatus(id, status, pageable);
    }

    @Override
    public List<Inscricao> buscarEmailEStatus(String id, StatusInscricao status, Pageable pageable) {
        return repositorio.findByEmailUsuarioAndStatus(id, status, pageable);
    }

    @Override
    public Inscricao cadastrar(Inscricao inscricao) {
        Inscricao reslt = repositorio.save(inscricao);
        JsonObject msg = new JsonObject();
        msg.addProperty("sendername", "EventService");
        msg.addProperty("senderemail", "joseifpb2015@gmail.com");
        msg.addProperty("receiveremail", inscricao.getEmailUsuario());
        //inscricaoServico.Validar(inscricao);
        if (inscricao != null) {
            msg.addProperty("subject", "Confirmação inscrição");
            msg.addProperty("text", "Bem vindo! sua incrição está confirmada no evento: " + inscricao.getTituloEvento() + " ");
            System.err.println("cadastra inscricao servi " + reslt);
            confirmaCadastro(msg);
        }
        return reslt;
    }

    @Override
    public boolean Validar(Inscricao inscricao) {
        try {
            String re = (String) restFull.buscarPorId(inscricao.getIdEvento(), "http://localhost:8081/api/eventos/validarinscricao/", Object.class);
            if (re.equalsIgnoreCase("ok")) {
                return true;
            }
        } catch (JSONException ex) {
            Logger.getLogger(InscricaoServicoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private int confirmaCadastro(JsonObject mensagem) {

        try {
            restFull.enviarMensagem(mensagem, Object.class,
                    env.getProperty("service.email"));
        } catch (IOException | JSONException ex) {
            return 0;
        }
        return 1;
    }

}
