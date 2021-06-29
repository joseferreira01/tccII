/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;

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
import com.gmail.joseifpb2015.TCC.entidades.Usuario;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.json.JSONException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<List<Inscricao>> listarPorId(@PathVariable(name = "id") String id) {
        List<Inscricao> in = new ArrayList<>();
        in.add(inscricaoServico.listarPorId(id));
        return ResponseEntity.ok(in);
    }

    @GetMapping(path = "/usuario/{email}")
    public ResponseEntity<List<Inscricao>> listarEmail(@PathVariable(name = "email") String email) {
        System.err.println("pegandp a inscricapo " + inscricaoServico.BuscarPorUserAndEvento("mariagv17111964@gmail.com", "60d0aacda1903d52527b81f4"));
        return ResponseEntity.ok((inscricaoServico.buscarPorEmail(email + ".com", null)));

        //return null;
    }

    @GetMapping(path = "/usuarioevento/{idevento}")
    public ResponseEntity<Inscricao> credenciar(@PathVariable(name = "idevento") String idevento, @RequestParam(name = "user") String user) {
        System.err.println("pegandp a inscricapo evento " + idevento + " " + user);
       
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            System.out.println("IP "+ip);
        } catch (Exception ex) {
            Logger.getLogger(InscricaoControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        // System.err.println("pegandp a inscricapo evento " +inscricaoServico.BuscarPorUserAndEvento(user+".com", idevento));
        return ResponseEntity.ok((inscricaoServico.BuscarPorUserAndEvento(user + ".com", idevento)));
        // return null;

        //return null;
    }

    @PostMapping
    public ResponseEntity<Inscricao> cadastrar(@Valid @RequestBody Inscricao inscricao, BindingResult result) throws IOException, JSONException {
        System.err.println("cadastrando inscriçao");
        try {

            if (inscricao != null) {
                Inscricao reslt = inscricaoServico.cadastrar(inscricao);
                System.err.println("cadastra inscricao servi " + reslt);
                return ResponseEntity.ok(reslt);
            }
            return ResponseEntity.ok(new Inscricao());
        } catch (Exception e) {
        }
        return null;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<Inscricao>> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Inscricao usuario, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<>(erros));
        }
        usuario.setId(id);
        return ResponseEntity.ok(new Response<>(inscricaoServico.atualizar(usuario)));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> remove(@PathVariable(name = "id") String id) {
        inscricaoServico.remover(id);
        return ResponseEntity.ok(1);

    }
}
