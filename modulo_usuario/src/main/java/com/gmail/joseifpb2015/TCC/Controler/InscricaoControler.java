/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;

import com.gmail.joseifpb2015.TCC.Servico.ClientRestFull;
import com.gmail.joseifpb2015.TCC.Servico.InscricaoServico;
import com.gmail.joseifpb2015.TCC.Servico.UserValidation;
import com.gmail.joseifpb2015.TCC.response.Response;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gmail.joseifpb2015.TCC.entidades.Inscricao;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.primefaces.json.JSONException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author jose
 */
@Configuration
@PropertySource("classpath:env/serve.properties")
@RestController
@RequestMapping(path = "/api/usuarios/inscricao")
//@RequestMapping(path = "/api/usuarios")
public class InscricaoControler {

    @Autowired
    private Environment env;

    private final UserValidation uv = new UserValidation();

    @Autowired
    private InscricaoServico inscricaoServico;
    @Autowired
    private ClientRestFull<Object> resf;

    @GetMapping
    public ResponseEntity<List<Inscricao>> listarTodos() {
        return ResponseEntity.ok((inscricaoServico.listarTodos()));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<Inscricao>> listarPorId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(new Response(inscricaoServico.listarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<Inscricao> cadastrar(@Valid @RequestBody Inscricao inscricao, BindingResult result) throws IOException, JSONException {
        System.err.println("cadastrando inscriçao");
        try {
            JsonObject msg = new JsonObject();
            msg.addProperty("remetente", "IF-eventos <" + inscricao.getEmailUsuario() + ">");
            msg.addProperty("destinatario", "IF-eventos <" + inscricao.getEmailUsuario() + ">");
           //inscricaoServico.Validar(inscricao);
            if (inscricao!=null) {
                Inscricao reslt = inscricaoServico.cadastrar(inscricao);
                msg.addProperty("assunto", "Confirmação inscrição");
                msg.addProperty("corpo", "Bem vindo! sua incrição está confirmada no evento: " + inscricao.getTituloEvento() + " ");
                System.err.println("cadastra inscricao servi "+reslt);
                confirmaCadastro(msg);

                return ResponseEntity.ok(reslt);
            }
            return ResponseEntity.ok(new Inscricao());
        } catch (Exception e) {
        }
        return null;
    }

    public int confirmaCadastro(JsonObject mensagem) {

        try {
            resf.enviarMensagem(mensagem, Object.class,
                    env.getProperty("service.email"));
        } catch (Exception ex) {
            return 0;
        }
        return 1;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<Inscricao>> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Inscricao usuario, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<Inscricao>(erros));
        }
        usuario.setId(id);
        return ResponseEntity.ok(new Response<Inscricao>(inscricaoServico.atualizar(usuario)));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> remove(@PathVariable(name = "id") String id) {
        inscricaoServico.remover(id);
        return ResponseEntity.ok(1);

    }
}
