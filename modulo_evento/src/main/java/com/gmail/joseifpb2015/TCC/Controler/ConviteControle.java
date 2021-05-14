/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;

import com.gmail.joseifpb2015.TCC.Servico.ClientRestFull;
import com.gmail.joseifpb2015.TCC.Servico.ConviteServico;
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
import com.gmail.joseifpb2015.TCC.entidade.Convite;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.primefaces.json.JSONException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose
 */
@Configuration
@PropertySource("classpath:env/serve.properties")
@RestController
@RequestMapping(path = "/api/eventos/convite")
//@RequestMapping(path = "/api/usuarios")
public class ConviteControle {

    @Autowired
    private Environment env;

    @Autowired
    private ConviteServico Servico;
    @Autowired
    private ClientRestFull<Object> restFull;

    @PostMapping(path = "/convidado")
    public ResponseEntity<List<Convite>> listarPorEmailConvidado(@Valid @RequestBody String email, BindingResult result) {
        email = email.split("\"")[1];
        System.err.println(" conve cc" + email);
        List<Convite> lis = Servico.findByemailDoConvidado(email);
        lis.forEach(System.out::print);
        return ResponseEntity.ok((lis));
    }

    @GetMapping(path = "/aceitar/{key}")
    public ResponseEntity<Response<String>> aceitarConvite(@PathVariable(name = "key") String key) {
        Servico.aceitarConvite(key);
        System.err.println("aceitar");
        return ResponseEntity.ok(new Response(new String("convite aceito")));
    }

    @GetMapping(path = "/recusar/{key}")
    public ResponseEntity<Response<String>> recusarConvite(@PathVariable(name = "key") String key) {
        try {
            Servico.regeitarConvite(key);
            System.err.println("recurar");
            return ResponseEntity.ok(new Response(new String("convite recusado")));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(new String("erro tente novamente")));
        }
    }

    @GetMapping(path = "/remetente/{idevento}")
    public ResponseEntity<List<Convite>> listarPorEmailRemetente(@PathVariable(name = "idevento") String idevento, Pageable pageable) {
        System.err.println("convite reme cont");
        return ResponseEntity.ok((Servico.listaConvites(idevento, pageable)));
    }

    @GetMapping()
    public ResponseEntity<List<Convite>> listar() {
        System.err.println("convite reme cont");
        return ResponseEntity.ok((Servico.listarTodos()));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<Convite>> listarPorId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(new Response(Servico.listarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<Response<Convite>> cadastrar(@Valid @RequestBody Convite convite, BindingResult result) throws IOException, JSONException {

        Convite resultado = null;

        JsonObject msg = new JsonObject();
        msg.addProperty("remetente", "IF-eventos <" + convite.getEmailremetente() + ">");
        msg.addProperty("destinatario", "IF-eventos <" + convite.getEmailDoConvidado() + ">");
        resultado = Servico.cadastrar(convite);
        if (resultado.getId() != null) {
            msg.addProperty("assunto", "Convite");
            msg.addProperty("corpo", "Bem vindo! " + convite.getNomeDoConvidado() + " \n\n "
                    + "você está sendo convidado(a) por " + convite.getRemetente() + " para participar do evento: \n\n"
                    + convite.getTituloEvento() + "\n\n que ocorre entre: " + convite.getDataEvento() + " e " + convite.getTerminoEvento()
                    + " para aceitar o convite cadastre-se no nosso site: www.if_eventos.com.br e  responda o convite por favor. ");
        } else {
            msg.addProperty("destinatario", "IF-eventos <" + convite.getEmailremetente() + ">");
            msg.addProperty("assunto", "erro ao enviar o convite ");
            msg.addProperty("corpo", "Hove um problema no convite de  " + convite.getEmailDoConvidado() + ""
                    + " para o evento \n \n" + convite.getTituloEvento());
        }
        confirmaCadastro(msg);

        return ResponseEntity.ok(new Response<>(convite));

    }

    public int confirmaCadastro(JsonObject mensagem) {

        try {
            restFull.enviarMensagem(mensagem, Object.class,
                    env.getProperty("service.email"));
        } catch (Exception ex) {
            return 0;
        }
        return 1;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<Convite>> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Convite usuario, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<>(erros));
        }
        usuario.setId(id);
        return ResponseEntity.ok(new Response<>(Servico.atualizar(usuario)));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> remove(@PathVariable(name = "id") String id) {
        Servico.remover(id);
        return ResponseEntity.ok(1);

    }
}
