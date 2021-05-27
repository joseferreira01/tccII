/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidades.Usuario;
import java.util.List;




/**
 *
 * @author jose
 */
public interface UsuarioServico extends Servico<Usuario> {

    public Usuario buscarPorEmail(String email);

    public Usuario login(String email, String senha);

    public Usuario habilitarConta(String codigo);
    public List<Usuario> findAllEmail(List<String> emails);
    @Override
     public Usuario cadastrar(Usuario usuario);
}
