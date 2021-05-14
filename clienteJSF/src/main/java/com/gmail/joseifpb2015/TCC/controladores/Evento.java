/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.controladores;

import com.gmail.joseifpb2015.TCC.Entidades.InfoAdicional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author jose
 */
@Document
public class Evento implements Serializable {
  @Id
    private String id;
    @Indexed(name = "TITULO_UNIQUE", unique = true)
    private String titulo;
    //@JsonFormat(pattern = "99/99/9999 99:99")
    private String dataInicio;
    private String inicioInscricao;
    private String terminoInscricao;
    // @JsonFormat(pattern = "99/99/9999 99:99")
    private String dataTernino;
    private Endereco endereco;
    private String descricao;
    private List<InfoAdicional> infoAdicionais;
    private List<String> colaboradores;
    private List<String> participantes;
    private List<String> palestrante;
    private List<byte[]> galeria;
    private String organizador;
    private String url;
    private int cargaHoraria;
     private String capa;
     private String form;

    public Evento() {
        this.endereco = new Endereco();
        this.colaboradores = new ArrayList<>();
        this.palestrante = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.infoAdicionais = new ArrayList<>();
        this.galeria = new ArrayList<>(10);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<String> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<String> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public List<String> getParticipantes() {
        return Collections.unmodifiableList(participantes);
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public List<String> getPalestrante() {
        return Collections.unmodifiableList(palestrante);
    }

    public void setPalestrante(List<String> palestrante) {
        this.palestrante = palestrante;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void addColaborador(String email) {
        colaboradores.add(email);
    }
    public void addParticipante(String email) {
        participantes.add(email);
    }
    public void addPalestrante(String email) {
        System.out.println("com.gmail.joseifpb2015.TCC.entidade.Evento.addPalestrante()");
        palestrante.add(email);
    }

    public void removerColaborador(List<String> emails) {
        colaboradores.removeAll(emails);
    }
    public void removerPalestrante(String emails) {
        palestrante.remove(emails);
    }
    public void removerParticipante(String email) {
        participantes.remove(email);
    }

    public void addInfo(InfoAdicional ia) {
        infoAdicionais.add(ia);
    }

    public void removerInfo(List<InfoAdicional> ia) {
        infoAdicionais.removeAll(ia);
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String Organizador) {
        this.organizador = Organizador;
    }

    public List<byte[]> getGaleria() {
        return galeria;
    }

    public void setGaleria(List<byte[]> galeria) {
        this.galeria = galeria;
    }

    public void setColaborador(List<String> emails) {
        this.colaboradores = emails;
    }

    public List<String> getColaborador() {
        return Collections.unmodifiableList(colaboradores);
    }

    public void addImg(byte[] img) {
        this.galeria.add(img);
    }

    public void removerImgGaleria(List<Byte[]> imgs) {
        galeria.removeAll(imgs);
    }

    public List<byte[]> getGalria() {
        return Collections.unmodifiableList(galeria);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    @NotEmpty(message = "Titulo nao pode ser vazio")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NotEmpty(message = "Descriçao nao pode ser vazio")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NotEmpty(message = "Data inicio não pode ser vazio")
    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    @NotEmpty(message = "Data termino não pode ser vazio")
    public String getDataTernino() {
        return dataTernino;
    }
     

    public void setDataTernino(String dataTernino) {
        this.dataTernino = dataTernino;
    }

    public List<InfoAdicional> getInfoAdicionais() {
        return infoAdicionais;
    }

    public void setInfoAdicionais(List<InfoAdicional> infoAdicionais) {
        this.infoAdicionais = infoAdicionais;
    }

    public String getInicioInscricao() {
        return inicioInscricao;
    }

    public void setInicioInscricao(String inicioInscricao) {
        this.inicioInscricao = inicioInscricao;
    }

    public String getTerminoInscricao() {
        return terminoInscricao;
    }

    public void setTerminoInscricao(String terminoInscricao) {
        this.terminoInscricao = terminoInscricao;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.titulo);
        hash = 13 * hash + Objects.hashCode(this.dataInicio);
        hash = 13 * hash + Objects.hashCode(this.dataTernino);
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataTernino, other.dataTernino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return new StringBuffer().append("Evento{" + "id=" + id + ", titulo="
                + titulo + ", dataInicio=" + dataInicio
                + ", dataTernino=" + dataTernino
                + ", endereco=" + endereco + ", descricao=" + descricao
                + ", infoAdicionais=" + infoAdicionais
                + ", colaboradores=" + colaboradores
                + ", capa=" + capa + ", galeria=" + galeria
                + ", Organizador=" + organizador
                + ", url=" + url + ", cargaHoraria=" + cargaHoraria + '}').toString();
    }

    private String DateInString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

    private LocalDateTime
            StringInDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

}
