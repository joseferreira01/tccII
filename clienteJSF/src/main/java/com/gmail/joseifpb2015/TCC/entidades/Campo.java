/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.joseifpb2015.TCC.entidades;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jose
 */
public class Campo implements Serializable{

    private String label;
    private String nome;
    private String tipo;
    private String pattern;
    private String id;
    private String dica;
    private List<String> opcoes;

    public Campo() {
    }

    public Campo(String label, String nome, String tipo, String id, String dica, List<String> opcoes) {
        this.label = label;
        this.nome = nome;
        this.tipo = tipo;
        this.id = id;
        this.dica = dica;
        this.opcoes = opcoes;
    }

    public String gerarHtml() {
        switch (tipo) {
            case "select":
                StringBuffer op = new StringBuffer();
                for (String s : opcoes) {
                    op.append("<option>" + s + "</option>");
                }
                return "<br/> <div class=orm-group" + ">"
                        + " <label for=" + this.id + ">" + this.label + "</label>"
                        + " <select class=\"form-control\" " + "id=" + this.id + " name= "+this.nome+ ">"
                        + op
                        + " </select>"
                        + "</div>";

            case "text":
                if(this.pattern!=null){
                return "<br/> <div class=form-group" + ">"
                        + " <label for=" + this.id + ">" + this.label + "</label>"
                        + " <input type=" + tipo + " id=" + this.id +" name= "+this.nome+ " class=\"form-control\" placeholder=\"" + this.dica  + "\" pattern=\"" +this.pattern+ "\"  />"
                        + "</div>";
                }
                else{  return "<br/> <div class=form-group" + ">"
                        + " <label for=" + this.id + ">" + this.label + "</label>"
                        + " <input type=" + tipo + " id=" + this.id +" name= "+this.nome+ " class=\"form-control\" placeholder=\"" + this.dica  + "\"   />"
                        + "</div>";
                }
            case "number":
                return "<br/> <div class=\"form-group\" " + ">"
                        + " <label for=" + this.id + ">" + this.label + "</label>"
                        + " <input type=" + tipo + " id=" + this.id +" name= "+this.nome+ " placeholder=\" " + this.dica + " \" />"
                        + "</div>";
            case "date":
                return "<br/> <div class=\"form-group\" " + ">"
                        + " <label for=" + this.id + ">" + this.label + "</label>"
                        + " <input class=\"form-control\" type=" + tipo + " id=" + this.id + " name= "+this.nome+ " pattern= \"[0-9]{4}-[0-9]{2}-[0-9]{2}\"  placeholder=" + this.dica + " />"
                        + "</div>";
            case "radio":
                return "<div class=\"form-group\" " + ">"
                        + " <input class=\"form-check-input\" type=" + tipo + " id=" + this.id +" name= "+this.nome+ " />"
                        + "</div>";
            case "checkbox":
                return "<div class= \"form-group\" " + ">"
                        + " <input class=\"form-check-input\" type=" + tipo + " id=" + this.id +" name= "+this.nome+ " placeholder= \"" + this.dica + " \" />"
                        + "</div>";
            case "textarea":
                return "<br/> <div class=\"form-group\" " + ">"
                        + " <label for=" + this.id + ">" + this.label + "</label>"
                        + " <input class=\"form-control \" type=" + tipo + " id=" + this.id +" name= "+this.nome+ " placeholder=\"" + this.dica + "\" rows=3 " + " />"
                        + "</div>";
            case "endereco":
                return endereco();

        }

        return "";

    }

    public String endereco() {
        StringBuffer op = new StringBuffer();
        for (String s : opcoes) {
            op.append("<option>" + s + "</option>");
        }
        return ""
                + "  <div class=\"form-group\">\n"
                + "    <label for=\"inputAddress\">Endereço</label>\n"
                + "    <input type=\"text\" name= "+this.nome+" class=\"form-control\" id=\"inputAddress\" placeholder=\"Rua dos Bobos, nº 0\">\n"
                + "  </div>\n"
                + "  <div class=\"form-row\">\n"
                + "    <div class=\"form-group col-md-6\">\n"
                + "      <label for=\"inputCity\">Cidade</label>\n"
                + "      <input type=\"text\" nome="+this.nome+" class=\"form-control\" id=\"inputCity\">\n"
                + "    </div>\n"
                + "    <div class=\"form-group col-md-4\">\n"
                + "      <label for=\"inputEstado\">Estado</label>\n"
                + "      <select id=\"inputEstado\"  nome="+this.nome+"  class=\"form-control\">\n"
                +       op
                + "      </select>\n"
                + "    </div>\n"
                + "    <div class=\"form-group col-md-2\">\n"
                + "      <label for=\"inputCEP\">CEP</label>\n"
                + "      <input type=\"text\"  nome="+this.nome+" class=\"form-control\" id=\"inputCEP\">\n"
                + "    </div>\n"
                + "  </div>\n"
                + "  <div class=\"form-group\">\n"
                + "    <div class=\"form-check\">\n"
               
                + "  </div>\n"
               ;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<String> opcoes) {
        this.opcoes = opcoes;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    

}
