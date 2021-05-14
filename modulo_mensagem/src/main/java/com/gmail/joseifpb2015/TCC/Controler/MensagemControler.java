/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Controler;


import com.gmail.joseifpb2015.TCC.response.Response;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gmail.joseifpb2015.TCC.Servico.EmailServico;
import com.gmail.joseifpb2015.TCC.email.Mensagem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;



/**
 *
 * @author jose
 */
@RestController
@RequestMapping(path = "/mensagem")
public class MensagemControler {
// validar datas da class evento nao esquecer
    @Autowired
    private EmailServico emailServico;
   

   
    @PostMapping
    @RequestMapping(path = "/enviar")
    public ResponseEntity<Response<String>> enviar(@Valid @RequestBody String msg, BindingResult result) {
          Gson gson = new GsonBuilder().create();
          Mensagem m3 = gson.fromJson(msg, Mensagem.class);
        if (result.hasErrors()) {
            List<String> erros = new ArrayList<>();
            result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<String>(erros));
        }
        System.err.println("de ,3"+ m3.toString());
        Mensagem m= new  Mensagem("Evento if <joseferreiravieira123@gmail.com>", 
                "jose <joseifpb2015@gmail.com>, Maria <mariagv17111964@gmail.com>", "teste 2019", msg);
        return ResponseEntity.ok(new Response<String>(emailServico.enviarEmail(m3)));
    }

   
}
