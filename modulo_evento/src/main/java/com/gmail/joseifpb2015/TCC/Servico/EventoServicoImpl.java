/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;


import com.gmail.joseifpb2015.TCC.entidade.Evento;
import com.gmail.joseifpb2015.TCC.repositorio.EventoRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service
public class EventoServicoImpl extends ServicoImpl<Evento,String> implements EventoServico{
    @Autowired
    private EventoRepositorio repositorio;
     @Autowired
    private ConverteStringLocalDate csld;


    @Override
    public List<Evento> listEventorPorOrg(String codigp, Pageable pageable) {
      return repositorio.findByorganizador(codigp, pageable);
        
    }

    @Override
    public String valivarDataInscricao(String IDEvento) {
        try {
            Evento evento = repositorio.findOne(IDEvento);
        System.err.println("valida data ss");
            boolean d = LocalDateTime.now().isAfter(csld.converteStringData(evento.getInicioInscricao()));
        
            boolean d2 = LocalDateTime.now().
                    isBefore(csld.converteStringData(evento.getTerminoInscricao()));
            System.err.println("d1 "+d+" d2 "+d2);
        
        
       if (LocalDateTime.now().isAfter(csld.converteStringData(evento.getInicioInscricao()))&&LocalDateTime.now().
                isBefore(csld.converteStringData(evento.getTerminoInscricao()))) {
           return "ok";
       }
        } catch (Exception e) {
            return "erro";
        }
       return "inscrições finalizadas";
    }

    
    
}
