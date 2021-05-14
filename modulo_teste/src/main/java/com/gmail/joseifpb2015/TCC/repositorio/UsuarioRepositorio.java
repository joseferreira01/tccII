/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.repositorio;

import com.gmail.joseifpb2015.TCC.Entidades.Usuario;

/**
 *
 * @author jose
 */
public interface UsuarioRepositorio extends Repositorio<Usuario,String>{
     public Usuario findByEmail(String email);
    
}
