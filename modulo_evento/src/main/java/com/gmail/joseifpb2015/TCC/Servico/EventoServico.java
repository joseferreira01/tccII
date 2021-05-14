/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidade.Evento;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose
 */
public interface EventoServico extends Servico<Evento> {

    public List<Evento> listEventorPorOrg(String codigp, Pageable pageable);

    public String valivarDataInscricao(String IDEvento);

}
