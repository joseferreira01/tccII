/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidades.Inscricao;
import com.gmail.joseifpb2015.TCC.entidades.StatusInscricao;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose
 */
public interface InscricaoServico extends Servico<Inscricao> {

    public List<Inscricao> buscarPorEmail(String id, Pageable pageable);

    public List<Inscricao> buscarPorIDEvento(String id, Pageable pageable);

    public List<Inscricao> buscarPorIDEventoEStatus(String id, StatusInscricao status, Pageable pageable);

    public List<Inscricao> buscarEmailEStatus(String id, StatusInscricao status, Pageable pageable);
    public boolean Validar(Inscricao inscricao);
}
