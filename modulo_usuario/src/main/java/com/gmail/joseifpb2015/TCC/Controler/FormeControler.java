/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;

import com.gmail.joseifpb2015.TCC.Servico.ClientRestFull;
import com.gmail.joseifpb2015.TCC.Servico.FormServico;
import com.gmail.joseifpb2015.TCC.Servico.UserValidation;
import com.gmail.joseifpb2015.TCC.entidades.Form;
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
import java.io.IOException;
import org.primefaces.json.JSONException;

/**
 *
 * @author jose
 */
@RestController
@RequestMapping(path = "/api/usuarios/forms")
//@RequestMapping(path = "/api/usuarios")
public class FormeControler {

    private final UserValidation uv = new UserValidation();

    @Autowired
    private FormServico servico;
    @Autowired
    private ClientRestFull<Object> resf;

    @GetMapping
    public ResponseEntity<List<Form>> listarTodos() {
        return ResponseEntity.ok((servico.listarTodos()));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Form>> listarPorId(@PathVariable(name = "id") String id) {
         System.out.println("origem 1");
         List<Form> forms = new ArrayList<>();
         forms.add(servico.listarPorId(id));
        return ResponseEntity.ok(forms);
       
   
    }
     @GetMapping(path = "/origem/{key}")
    public ResponseEntity<Response<Form>> listarPorKey(@PathVariable(name = "key") String id) {
         System.out.println("origem "+id);
        return ResponseEntity.ok(new Response(servico.findByKeyOrigem(id)));
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody Form forme, BindingResult result) throws IOException, JSONException {
        System.err.println("cad isc colt");
        try {
           
            
           
         

            return ResponseEntity.ok(servico.cadastrar(forme).getId());
           
        } catch (Exception e) {
        }
  return null;
    }

   

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<Form>> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Form form, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<Form>(erros));
        }
        form.setId(id);
        return ResponseEntity.ok(new Response<Form>(servico.atualizar(form)));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> remove(@PathVariable(name = "id") String id) {
        servico.remover(id);
        return ResponseEntity.ok(1);

    }
}


