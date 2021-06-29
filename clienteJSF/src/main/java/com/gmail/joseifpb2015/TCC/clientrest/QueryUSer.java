/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.clientrest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class QueryUSer {
    private List<String> id;

    public QueryUSer() {
        this.id =new ArrayList<>();
    }
    

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QueryUSer{" + "id=" + id + '}';
    }
    
    
}
