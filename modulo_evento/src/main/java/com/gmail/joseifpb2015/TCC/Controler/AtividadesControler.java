/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;

import com.gmail.joseifpb2015.TCC.Servico.AtividadeServico;
import com.gmail.joseifpb2015.TCC.entidade.Atividade;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(path = "/api/atividades")
public class AtividadesControler {
// validar datas da class evento nao esquecer

    @Autowired
    private AtividadeServico AtividadeServico;

    @GetMapping
    public ResponseEntity<List<Atividade>> listarTodos() {
        System.err.println("lista at c");
        return ResponseEntity.ok(AtividadeServico.listarTodos());
    }

    @GetMapping(path = "/{idevento}")
    public ResponseEntity<List<Atividade>> listarPorId(@PathVariable(name = "idevento") String id, Pageable pageable) {
         System.err.println("serviço de paginação");
        return ResponseEntity.ok(AtividadeServico.listTodosAtividade(id, pageable));
    }

    @PostMapping
    public ResponseEntity<Atividade> cadastrar(@Valid @RequestBody Atividade atividade, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(atividade);

        }

        return ResponseEntity.ok(AtividadeServico.cadastrar(atividade));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Atividade> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Atividade atividade, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Atividade());
        }
        atividade.setId(id);
        return ResponseEntity.ok(AtividadeServico.atualizar(atividade));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable(name = "id") String id) {
        try {
            AtividadeServico.remover(id);
            
        } catch (Exception e) {
            return ResponseEntity.ok(0);
        }
        return ResponseEntity.ok(1);

    }
}
