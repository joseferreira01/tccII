/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.Entidades.Usuario;
import com.gmail.joseifpb2015.TCC.repositorio.UsuarioRepositorio;
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
    
    
}
