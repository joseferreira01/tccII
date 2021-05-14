/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidades.Form;
import com.gmail.joseifpb2015.TCC.repositorio.FormRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service
public class FormServicoImpl extends ServicoImpl<Form, String> implements FormServico {

    @Autowired
    private FormRepositorio repositorio;

    @Override
    public List<Form> findByKeyOrigem(String origem) {
        System.out.println("form "+ origem);
        return repositorio.findByOrigem(origem);
    }

 

       }


