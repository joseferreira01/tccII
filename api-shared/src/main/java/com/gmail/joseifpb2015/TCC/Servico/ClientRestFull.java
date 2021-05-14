/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Servico;

import java.io.IOException;
import org.primefaces.json.JSONException;

/**
 *
 * @author jose
 */
public interface ClientRestFull<T> {

    String enviarMensagem(T obj, Class<T> type, String uri) throws IOException, JSONException;
    public T buscarPorId(String id,String uri, Class<T> type ) throws JSONException;

  
    
}
