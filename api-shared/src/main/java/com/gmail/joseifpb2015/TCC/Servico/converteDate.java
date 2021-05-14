/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author jose
 */
public interface converteDate {

    String converteDataString(LocalDateTime data);

    LocalDateTime converteStringData(String data);
    
}
