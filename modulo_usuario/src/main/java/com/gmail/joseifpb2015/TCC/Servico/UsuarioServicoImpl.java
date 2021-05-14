/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;


import com.gmail.joseifpb2015.TCC.entidades.Usuario;
import com.gmail.joseifpb2015.TCC.repositorio.UsuarioRepositorio;
import com.gmail.joseifpb2015.TCC.entidades.Status;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service
public class UsuarioServicoImpl extends ServicoImpl<Usuario,String> implements UsuarioServico{
@Autowired
    private UsuarioRepositorio repositorio;
    @Override
    public Usuario login(String email, String senha) {
    Usuario u = repositorio.findByEmail(email);
       if(u!=null&& u.getSenha().equals(senha))
           return u;
    return new Usuario();
    
    }
    public Usuario buscarPorEmail(String email) {
    Usuario u = repositorio.findByEmail(email);
       if(u!=null)
           return u;
    return new Usuario();
    
    }
    
     @Override
    public Usuario habilitarConta(String codigo) {
         Usuario u = repositorio.findById(codigo);
        if (u != null ) {
            u.setStatus(Status.HABILITADO);
            repositorio.save(u);
            
            return u;
        }
        return new Usuario();
    }

    @Override
    public List<Usuario> findAllEmail(List<String> emails) {
    Iterable<Usuario> us = repositorio.findAll(emails);
        
      return StreamSupport.stream(us.spliterator(), false)
                        .collect(Collectors.toList());
    }
}
