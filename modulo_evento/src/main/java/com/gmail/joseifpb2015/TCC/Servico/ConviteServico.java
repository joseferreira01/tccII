/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidade.Convite;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose
 */
public interface ConviteServico extends Servico<Convite> {

    public List<Convite> findByemailremetente(String id, Pageable pageable);

    public List<Convite> findByemailDoConvidado(String email);
    public  void aceitarConvite(String key);
    public  void regeitarConvite(String key);
    public List<Convite> listaConvites(String idEvento,Pageable pageable);

}
