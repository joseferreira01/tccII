/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import java.util.List;

/**
 *
 * @author jose
 * @param <T>
 */
public interface Servico<T> {

    List<T> listarTodos();

    T listarPorId(String id);

    T cadastrar(T entidade);

    T atualizar(T entidade);

    void remover(String id);
}
