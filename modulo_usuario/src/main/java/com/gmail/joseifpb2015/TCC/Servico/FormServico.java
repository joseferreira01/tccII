/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import com.gmail.joseifpb2015.TCC.entidades.Form;
import java.util.List;

/**
 *
 * @author jose
 */
public interface FormServico extends Servico<Form> {
 public List<Form> findByKeyOrigem(String key);
  
}
