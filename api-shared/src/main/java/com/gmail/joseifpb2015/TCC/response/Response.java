/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.response;

import java.util.List;

/**
 *
 * @author jose
 */
public class Response<T> {
    private T data;
    private List<String> erros;

    public Response(T data) {
        this.data = data;
       
    }
    public Response(List<String> erros) {
        this.erros = erros;
       
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
    
    
}
