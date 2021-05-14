/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.repositorio;

import com.gmail.joseifpb2015.TCC.entidade.Convite;
import com.gmail.joseifpb2015.TCC.entidade.StatusConvite;
import com.gmail.joseifpb2015.TCC.entidade.TipoConvidado;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose
 */
public interface ConviteRepositorio extends Repositorio<Convite, String> {

    public List<Convite> findByemailremetente(String email, Pageable pageable);
    public List<Convite> findByidEvento(String idEvento, Pageable pageable);

    public List<Convite> findByemailDoConvidadoAndStatusConvite(String email,StatusConvite status);

    public Convite findByemailDoConvidado(String email);

}
