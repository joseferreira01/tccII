/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.repositorio;

import com.gmail.joseifpb2015.TCC.entidade.Atividade;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose
 */
public interface AtividadeRepositorio extends Repositorio<Atividade,String>{
     public List<Atividade> findByidEvento(String codigp,  Pageable pageable);
   
    
}
