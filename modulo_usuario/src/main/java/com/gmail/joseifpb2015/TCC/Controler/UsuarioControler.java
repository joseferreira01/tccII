/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;

import com.gmail.joseifpb2015.TCC.Servico.ClientRestFull;
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
import com.gmail.joseifpb2015.TCC.Servico.UsuarioServico;
import com.gmail.joseifpb2015.TCC.entidades.Usuario;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
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
@RequestMapping(path = "/api/usuarios")
//@RequestMapping(path = "/api/usuarios")
public class UsuarioControler {
      @Autowired
	private Environment env;

    private final UserValidation uv = new UserValidation();

    @Autowired
    private UsuarioServico usuarioServico;
    @Autowired
    private ClientRestFull<Object> restFull;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        System.err.println("docker ok");
        return ResponseEntity.ok((usuarioServico.listarTodos()));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<Usuario>> listarPorId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(new Response(usuarioServico.listarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<Response<Usuario>> cadastrar(@Valid @RequestBody Usuario usuario, BindingResult result) throws IOException, JSONException {
        Map<String, String> execute = uv.execute(usuario);
        Usuario resultado = null;
        execute.remove("passou");
        JsonObject msg = new JsonObject();
        msg.addProperty("remetente", "IF-eventos <" + usuario.getEmail() + ">");
        msg.addProperty("destinatario", "IF-eventos <" + usuario.getEmail() + ">");

        int index = 0;

        if (result.hasErrors() || !execute.isEmpty()) {

            List<String> erros = new ArrayList<>();
            for (String value : execute.values()) {
                erros.add(value);
            }
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<Usuario>(erros));
        }
        if (usuario.getId() != null) {
            Usuario u = new Usuario();
            try {
                u = usuarioServico.buscarPorEmail(usuario.getEmail());
            } catch (Exception e) {
                u.setEmail("fack@gmail.com");
            }

            if (u.getEmail() != null && u.getEmail().equals(usuario.getEmail())) {
                resultado = usuarioServico.atualizar(usuario);
            } else {
                msg.addProperty(" assunto", "Atualização de Email");
                msg.addProperty("corpo", "Olã esse é seus novo Email no IF-Eventos use para fazer logim no nosso servico : ");
                resultado = usuarioServico.atualizar(usuario);
                index = 1;
            }
        } else {
            resultado = usuarioServico.cadastrar(usuario);
            msg.addProperty("assunto", "Confirmação de cadastro");
            msg.addProperty("corpo", "Bem vindo! Para habilitar sua conta e pode usar nosso servico use o código -> "
                    + resultado.getId());
            index = 1;
        }

        if (resultado != null && resultado.getId() != null) {
            if (index == 1) {
                confirmaCadastro(msg);
            }
            return ResponseEntity.ok(new Response<Usuario>(usuario));
        }

        return null;
    }

    public int confirmaCadastro(JsonObject mensagem) {

        try {
            restFull.enviarMensagem(mensagem, Object.class,
                    env.getProperty("service.email"));
        } catch (IOException | JSONException ex) {
            return 0;
        }
        return 1;
    }

    @PostMapping
    @RequestMapping(path = "/login")
    public ResponseEntity<List<Usuario>> login(@Valid @RequestBody Usuario usuario, BindingResult result) {
        Usuario login = null;
        List<Usuario> us = new ArrayList<Usuario>();
        try {
            login = usuarioServico.login(usuario.getEmail(), usuario.getSenha());
            //if(login!= null & )
            us.add(login);
        } catch (Exception e) {
            us.add(new Usuario());
        }

        us.add(login);
        return ResponseEntity.ok(us);
    }
    @PostMapping
    @RequestMapping(path = "/convite")
    public ResponseEntity<List<Usuario>> convitarUsuaria( @Valid @RequestBody Usuario usuario, BindingResult result) {
        Usuario login = null;
        List<Usuario> us = new ArrayList<Usuario>();
        try {
            login = usuarioServico.buscarPorEmail(usuario.getEmail());
            if(login!= null){
           
            usuarioServico.atualizar(login);
        }
           usuario.setSenha(null);
           usuarioServico.cadastrar(usuario);
           
        } catch (Exception e) {
            us.add(new Usuario());
        }

        us.add(login);
        return ResponseEntity.ok(us);
    }

    @PostMapping
    @RequestMapping(path = "/habilitar")
    public ResponseEntity<List<Usuario>> habilitarConta(@Valid @RequestBody Usuario usuario, BindingResult result) {

        Usuario user = usuarioServico.habilitarConta(usuario.getId());

        List<String> erros = new ArrayList<>();
        List<Usuario> us = new ArrayList<>();
        us.add(user);
        if (!erros.isEmpty() || user.getId().equals("")) {
            erros.add("Dados do usuário inválidos ");
            return ResponseEntity.badRequest().body(Collections.EMPTY_LIST);
        }

        return ResponseEntity.ok(us);
    }

    @PostMapping
    @RequestMapping(path = "/reenviaremail")
    public ResponseEntity<List<String>> reEviarEmail(@Valid @RequestBody Usuario usuario, BindingResult result) {

        Usuario user = usuarioServico.buscarPorEmail(usuario.getEmail());

        List<String> retorno = new ArrayList<>();

        if (!retorno.isEmpty() || user.getId().equals("")) {
            retorno.add("Dados do usuário inválidos ");
            return ResponseEntity.badRequest().body(Collections.EMPTY_LIST);
        }
        retorno.add("Conta ativada com sucesso");
        System.err.println("enviando cod " + retorno.get(0));
        JsonObject msg = new JsonObject();
        msg.addProperty("remetente", "IF-eventos <" + usuario.getEmail() + ">");
        msg.addProperty("destinatario", "IF-eventos <" + usuario.getEmail() + ">");
        msg.addProperty("assunto", "Codigo de validaçao");
        msg.addProperty("corpo", "Olá "
                + user.getNome() + ",\n Esse Código de validaçao e'pessoal e intransferivel: "
                + "código -> " + user.getId());

        confirmaCadastro(msg);
        return ResponseEntity.ok(retorno);
    }

    @PostMapping
    @RequestMapping(path = "/novaSenha")
    public ResponseEntity<List<String>> recuperaSenha(@Valid @RequestBody Usuario usuario, BindingResult result) {

        Usuario user = null;
        List<String> retorno = new ArrayList<>();

        try {
            user = usuarioServico.listarPorId(usuario.getId());
            String senha = usuario.getSenha();
            user.setSenha(senha);
            usuarioServico.atualizar(user);
            retorno.add("Email enviado com sucesso");

        } catch (Exception e) {
            retorno.add("erro tente novamente");
        }

        return ResponseEntity.ok(retorno);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(null);
        }
        usuario.setId(id);
        return ResponseEntity.ok(usuarioServico.atualizar(usuario));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> remove(@PathVariable(name = "id") String id) {
        usuarioServico.remover(id);
        return ResponseEntity.ok(1);

    }
}
