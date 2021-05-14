/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service
public class ConverteStringLocalDate implements converteDate {

    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public LocalDateTime converteStringData(String data) {
        return LocalDateTime.parse(data, formato);
    }
    @Override
    public String converteDataString(LocalDateTime data) {
        return formato.format(data);
    }

}
