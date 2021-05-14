/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.repositorio.Repositorio;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jose
 */

public class ServicoImpl<T,KEY extends Serializable> implements Servico<T>{
    
    @Autowired
    private Repositorio<T,KEY> repositorio;
    
    @Override
    public List<T> listarTodos() {
      return repositorio.findAll();
    }

    @Override
    public T listarPorId(String id) {
      return repositorio.findOne(id);
    }

    @Override
    public T cadastrar(T entidade) {
        return repositorio.save(entidade);
    }

    @Override
    public T atualizar(T entidade) {
        return repositorio.save(entidade);
    }

    @Override
    public void remover(String id) {
       repositorio.delete(id);
    }
    
}
