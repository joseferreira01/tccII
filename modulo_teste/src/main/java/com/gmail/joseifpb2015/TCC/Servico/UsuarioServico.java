/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.Entidades.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
public interface UsuarioServico extends Servico<Usuario>{
    

    public Usuario login(String email, String senha);

  
}
