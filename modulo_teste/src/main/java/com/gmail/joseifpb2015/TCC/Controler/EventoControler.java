/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;

import com.gmail.joseifpb2015.TCC.Entidades.Evento;
import com.gmail.joseifpb2015.TCC.Servico.EventoServico;
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

/**
 *
 * @author jose
 */
@RestController
@RequestMapping(path = "/eventos")
public class EventoControler {
// validar datas da class evento nao esquecer
    @Autowired
    private EventoServico eventoServico;

    @GetMapping
    public ResponseEntity<Response<List<Evento>>> listarTodos() {
        return ResponseEntity.ok(new Response<List<Evento>>(eventoServico.listarTodos()));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<Evento>> listarPorId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(new Response(eventoServico.listarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<Response<Evento>> cadastrar(@Valid @RequestBody Evento evento, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<Evento>(erros));
        }
        return ResponseEntity.ok(new Response<Evento>(eventoServico.cadastrar(evento)));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<Evento>> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Evento evento, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<Evento>(erros));
        }
        evento.setId(id);
        return ResponseEntity.ok(new Response<Evento>(eventoServico.atualizar(evento)));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response<Integer>> delete(@PathVariable(name = "id") String id) {
        eventoServico.remover(id);
        return ResponseEntity.ok(new Response<Integer>(1));

    }
}
