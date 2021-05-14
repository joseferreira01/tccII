/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidade.Atividade;
import com.gmail.joseifpb2015.TCC.repositorio.AtividadeRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service
public class AtividadeServicoImpl extends ServicoImpl<Atividade,String> implements AtividadeServico{
    
  @Autowired
    private AtividadeRepositorio repositorio;

    @Override
    public List<Atividade> listTodosAtividade( String codigo,Pageable pageable) {
        return repositorio.findByidEvento(codigo, pageable);
    }


    
   
}
