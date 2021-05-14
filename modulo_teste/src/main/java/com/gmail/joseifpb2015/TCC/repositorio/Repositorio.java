/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.repositorio;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author jose
 * @param <T>
 * @param <id>
 */
@NoRepositoryBean
public interface Repositorio<T,id extends Serializable> extends MongoRepository<T, String>{
    
}
