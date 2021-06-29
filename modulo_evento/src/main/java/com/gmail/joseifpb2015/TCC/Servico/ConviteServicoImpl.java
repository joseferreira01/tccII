/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidade.Convite;
import com.gmail.joseifpb2015.TCC.entidade.Evento;
import com.gmail.joseifpb2015.TCC.entidade.StatusConvite;
import com.gmail.joseifpb2015.TCC.repositorio.ConviteRepositorio;
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
public class ConviteServicoImpl extends ServicoImpl<Convite, String> implements ConviteServico {

    @Autowired
    private ConviteRepositorio repositorio;
    @Autowired
    private EventoServico er;
    @Autowired
    private ConverteStringLocalDate csld;

    @Override
    public List<Convite> findByemailremetente(String email, Pageable pageable) {
        return repositorio.findByemailremetente(email, pageable);
    }

    @Override
    public List<Convite> findByemailDoConvidado(String email) {
        return repositorio.findByemailDoConvidadoAndStatusConvite(email,StatusConvite.AGUARDANDO);
    }

    @Override
    public void aceitarConvite(String key) {
        Convite convite = repositorio.findOne(key);
        Evento evento = er.listarPorId(convite.getIdEvento());

        if (LocalDateTime.now().
                isBefore(csld.converteStringData(convite.getTerminoEvento()))) {
            switch (convite.getTipo()) {

                case PALESTRANTE:
                    System.err.println("apl palestrante");
                    evento.addPalestrante(convite.getEmailDoConvidado());
                    break;
                case PARTICIPANTE:
                    evento.addParticipante(convite.getEmailDoConvidado());
                    break;
                case COLABORADOR:
                    evento.addColaborador(convite.getEmailDoConvidado());
                    break;
                default:
                    System.err.println("t case");
            }
            try {
                er.atualizar(evento);
                convite.setStatusConvite(StatusConvite.CONFIRMADO);
                repositorio.save(convite);
            } catch (Exception e) {
            }
        }

    }

    @Override
    public void regeitarConvite(String key) {
        try {
             Convite conv = repositorio.findOne(key);
             System.err.println("ser re convite "+conv.getId());
             conv.setStatusConvite(StatusConvite.RECUSOU);
             repositorio.save(conv);
        } catch (Exception e) {
        }
        
    }

    @Override
    public List<Convite> listaConvites(String idEvento, Pageable pageable) {
        return repositorio.findByidEvento(idEvento,pageable);
         }

}
