/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.entidades;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author jose
 */
@Document
public class Usuario implements Serializable {

    private String nome;
    @Id
    private String id;
    @Indexed(name = "EMAIL_UNIQUE", unique = true)
    private String email;
    private String senha;
    private List<InfoAdicional> infoAdicionais;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Status status;
    private String lattes;
    private String linkedIn;
    private String biografia;
    private String foto;

    public Usuario() {
        this.infoAdicionais = new ArrayList<>();
        this.status = Status.DESABILITADO;
    }

    public Usuario(String nome, String email, String senha, List<InfoAdicional> infoAdicionais, Status status) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.infoAdicionais = infoAdicionais;
        this.status = status;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void addInfo(InfoAdicional ia) {
        for (InfoAdicional info : infoAdicionais) 
            if(info.getKey().equalsIgnoreCase(ia.getKey())){
                removeInfo(info);
                 this.infoAdicionais.add(ia);
                 return;
            }else{ infoAdicionais.add(ia);}
        
    }

    public void removeInfo(InfoAdicional ia) {
        this.infoAdicionais.remove(ia);
    }

    @NotEmpty(message = "nome não pode ser vazio")
    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    @NotEmpty(message = "CPF não pode ser vazio" )
//    @CPF(message = "CPF inválido")
//    public String getCPF() {
//        return CPF;
//    }
//
//    public void setCPF(String CPF) {
//        this.CPF = CPF;
//    }
    @NotEmpty(message = "Email não pode ser vazio")
    @Email(message = "Email invalido")

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "Senha não pode ser vazio")
    @Size(min = 8, message = "A senha deve ter no minimo 8 digitos")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<InfoAdicional> getInfoAdicionais() {
        return infoAdicionais;
    }

    public void setInfoAdicionais(List<InfoAdicional> infoAdicionais) {
        this.infoAdicionais = infoAdicionais;
    }

    @Override
    public int hashCode() {
        int hash = 5;

        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.senha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;

        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario" + "nome" + nome + ", id" + id + ", email" + email + ", senha" + senha + ", infoAdicionais" + infoAdicionais + ", status" + status;
    }

}
