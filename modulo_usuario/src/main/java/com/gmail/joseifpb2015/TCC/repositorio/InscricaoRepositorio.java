/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.repositorio;

import com.gmail.joseifpb2015.TCC.entidades.Inscricao;
import com.gmail.joseifpb2015.TCC.entidades.StatusInscricao;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose
 */
public interface InscricaoRepositorio extends Repositorio<Inscricao, String> {

     public List<Inscricao> findByEmailUsuario(String id, Pageable pageable);

     public List<Inscricao> findByidEvento(String id, Pageable pageable);

     public List<Inscricao> findByidEventoAndStatus(String id, StatusInscricao status, Pageable pageable);

     public List<Inscricao> findByEmailUsuarioAndStatus(String id, StatusInscricao status, Pageable pageable);

}
