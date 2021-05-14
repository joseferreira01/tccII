/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.Entidades;

import java.io.Serializable;
import javax.persistence.Column;

/**
 *
 * @author jose
 * @param <T>
 */
public class InfoAdicional<T> implements Serializable {

    @Column(nullable = false)
    private T info;
    @Column(nullable = false)
    private String key;

    public InfoAdicional() {
    }

    public InfoAdicional(T info, String key) {
        this.info = info;
        this.key = key;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    

}
